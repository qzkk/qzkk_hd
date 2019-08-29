package com.qzkk.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.qzkk.domain.Task;
import com.qzkk.service.TaskService;
import com.qzkk.vo.ExcelOfTask;
import com.qzkk.vo.SelectTaskCondition;
import com.qzkk.vo.TeamVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/getTaskList")
    public JSONObject getTaskList(@RequestParam long tid) {
        return taskService.getTaskList(tid);
    }
    @PostMapping("/getTaskList1")
    public JSONObject getTaskList1() {
        return taskService.getTaskList1();
    }
    @PostMapping("/getTaskListByUid")
    public JSONObject getTaskListByUid(@RequestParam long uid) {
        return taskService.getTaskListByUid(uid);
    }
    @PostMapping("/getTaskListOfAccessByUid")
    public JSONObject getTaskListOfAccessByUid(@RequestParam long uid) {
        return taskService.getTaskListOfAccessByUid(uid);
    }
    @PostMapping("/getTaskListByExamine")
    public JSONObject getTaskListByExamine() {
        return taskService.getTaskListByExamine();
    }
    @PostMapping("/getPassedTaskListByUid")
    public JSONObject getPassedTaskListByUid(@RequestParam long uid) {
        return taskService.getPassedTaskListByUid(uid);
    }
    @PostMapping("/selectTeamNotDis")
    public JSONObject selectTeamNotDis(@RequestParam long taid,@RequestParam long uid) {
        return taskService.selectTeamNotDis(taid,uid);
    }
    @PostMapping("/selectChargedTeam")
    public JSONObject selectChargedTeam(@RequestParam long uid) {
        return taskService.selectChargedTeam(uid);
    }

    @PostMapping("/deleteTask")
    public JSONObject deleteTask(@RequestParam long id) {
        return taskService.deleteTask(id);
    }
    @PostMapping("/viewTeamsByTaskId")
    public JSONObject viewTeamsByTaskId(@RequestParam long taid) {
        return taskService.viewTeamsByTaskId(taid);
    }
    @PostMapping("/aplyTask")
    public JSONObject aplyTask(@RequestBody String aplyTask) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(aplyTask);
        JsonElement taskEle=jsonObject.get("task");
        JsonArray jsonArray= jsonObject.getAsJsonArray("teamTaskList");
        Gson gson=new Gson();
        Task task=gson.fromJson(taskEle,new TypeToken<Task>(){}.getType());
        List<TeamVO> teamVOS=new ArrayList<>();
        for (JsonElement teamTaEle:jsonArray){
            TeamVO teamVO=gson.fromJson(teamTaEle,new TypeToken<TeamVO>(){}.getType());
            teamVOS.add(teamVO);
        }

        return taskService.aplyTask(teamVOS,task);
    }
    @PostMapping("/distributeTa")
    public JSONObject distributeTa(@RequestBody String teamTaskList) {

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(teamTaskList);
        JsonArray jsonArray= jsonObject.getAsJsonArray("teamTaskList");
        long taid=jsonObject.get("taid").getAsLong();
        Gson gson=new Gson();
        List<TeamVO> teamVOS=new ArrayList<>();
        for (JsonElement teamTaEle:jsonArray){
            TeamVO teamVO=gson.fromJson(teamTaEle,new TypeToken<TeamVO>(){}.getType());
            teamVOS.add(teamVO);
        }

        return taskService.distributeTa(teamVOS,taid);
    }
    @PostMapping("/operateTask")
    public JSONObject operateTask(@RequestParam long taid,@RequestParam int state) {
        return taskService.operateTask(taid,state);
    }
    @PostMapping("/viewMemeberByTid")
    public JSONObject viewMemeberByTid(@RequestParam long tid) {
        return taskService.viewMemeberByTid(tid);
    }
    @PostMapping("/getTaskListToPage")
    public JSONObject getTaskListToPage(SelectTaskCondition stc) {
        return taskService.getTaskListToPage(stc);
    }

    @GetMapping("/exportTask")
    public String exportExcelTest(HttpServletResponse response, HttpServletRequest request){
        String taskList=request.getParameter("taskList");
        Gson gson=new Gson();
        List<ExcelOfTask> data=gson.fromJson(taskList,new TypeToken<List<ExcelOfTask>>(){}.getType());
        for(int i=0;i<data.size();i++){
            if (data.get(i).getState().equals("0")){
                data.get(i).setState("待审核");
            }else if (data.get(i).getState().equals("1")){
                data.get(i).setState("审核通过");
            }else if (data.get(i).getState().equals("-1")){
                data.get(i).setState("审核被拒绝");
            }

        }
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(data) ;
        // 判断数据
        if(workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "task" ;
        // 重置响应对象
        response.reset();
        // 当前日期，用于导出文件名称
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = excelName+System.currentTimeMillis();
        // 指定下载的文件名--设置响应头
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" +dateStr+".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        //response.setHeader("X-Powered-By",' 3.2.1');
        response.setHeader("Content-Type", "application/json;charset=utf-8");

        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public Workbook exportSheetByTemplate(List<ExcelOfTask> list){

        // 设置导出配置
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams("src/main/java/com/qzkk/controller/task.xlsx");
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("科考任务信息");
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("list",list) ;
        // 导出excel
        return ExcelExportUtil.exportExcel(params, map);
    }

}

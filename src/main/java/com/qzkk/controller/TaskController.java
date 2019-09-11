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

    /**
     * 获得该团队的科考任务
     * @param tid
     * @return
     */
    @PostMapping("/getTaskList")
    public JSONObject getTaskList(@RequestParam long tid) {
        return taskService.getTaskList(tid);
    }

    /**
     * 获取所有任务
     * @return
     */
    @PostMapping("/getTaskList1")
    public JSONObject getTaskList1() {
        return taskService.getTaskList1();
    }

    /**
     * 获取该队长的任务列表
     * @param uid 负责人id
     * @return
     */
    @PostMapping("/getTaskListByUid")
    public JSONObject getTaskListByUid(@RequestParam long uid) {
        return taskService.getTaskListByUid(uid);
    }

    /**
     * 获取要审核的任务
     * @return
     */
    @PostMapping("/getTaskListByExamine")
    public JSONObject getTaskListByExamine() {
        return taskService.getTaskListByExamine();
    }

    /**
     * 获取该负责人名下已经申请通过的任务
     * @param uid 负责人id
     * @return
     */
    @PostMapping("/getPassedTaskListByUid")
    public JSONObject getPassedTaskListByUid(@RequestParam long uid) {
        return taskService.getPassedTaskListByUid(uid);
    }

    /**
     * 获取该队长所拥有的小队，并且这些小队还没有分配该任务
     * @param taid 任务id
     * @param uid  负责人id
     * @return
     */
    @PostMapping("/selectTeamNotDis")
    public JSONObject selectTeamNotDis(@RequestParam long taid,@RequestParam long uid) {
        return taskService.selectTeamNotDis(taid,uid);
    }

    /**
     * 申请任务时选择负责小队的列表
     * @param uid 负责人id
     * @return
     */
    @PostMapping("/selectChargedTeam")
    public JSONObject selectChargedTeam(@RequestParam long uid) {
        return taskService.selectChargedTeam(uid);
    }

    /**
     * 删除该任务
     * @param id 任务id
     * @return
     */
    @PostMapping("/deleteTask")
    public JSONObject deleteTask(@RequestParam long id) {
        return taskService.deleteTask(id);
    }

    /**
     * 根据任务id查看负责此任务的的团队
     * @param taid  任务id
     * @return
     */
    @PostMapping("/viewTeamsByTaskId")
    public JSONObject viewTeamsByTaskId(@RequestParam long taid) {
        return taskService.viewTeamsByTaskId(taid);
    }

    /**
     * 任务申请
     * @param aplyTask
     * @return
     */
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

    /**
     * 给的多个小队分配任务
     * @param teamTaskList 例如：{"teamTaskList":[],"taid":1}
     * @return
     */
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

    /**
     * 审批任务同意或者拒绝
     * @param taid  任务id
     * @param state 任务的审核状态：0待审核，1审核通过，-1审核不通过
     * @return
     */
    @PostMapping("/operateTask")
    public JSONObject operateTask(@RequestParam long taid,@RequestParam int state) {
        return taskService.operateTask(taid,state);
    }

    /**
     * 根据团队id查看团队成员
     * @param tid  团队id
     * @return
     */
    @PostMapping("/viewMemeberByTid")
    public JSONObject viewMemeberByTid(@RequestParam long tid) {
        return taskService.viewMemeberByTid(tid);
    }

    /**
     * 查看任务并且分页
     * @param stc 查看的条件，暂时包括：任务名称、任务申请人、任务审核状态
     * @return
     */
    @PostMapping("/getTaskListToPage")
    public JSONObject getTaskListToPage(SelectTaskCondition stc) {
        return taskService.getTaskListToPage(stc);
    }


//    @PostMapping("/getTaskListOfAccessByUid")
//    public JSONObject getTaskListOfAccessByUid(@RequestParam long uid) {
//        return taskService.getTaskListOfAccessByUid(uid);
//    }
//
//    @PostMapping("/getTaskListOfAccessByUid")
//    public JSONObject getTaskListOfAccessByUid(@RequestParam long uid) {
//        return taskService.getTaskListOfAccessByUid(uid);
//    }

    /**
     * 获得该队长审核已通过的任务
     * @param uid
     * @return
     */
    @PostMapping("/getTaskListOfAccessByUid")
    public JSONObject getTaskListOfAccessByUid(@RequestParam long uid) {
        return taskService.getTaskListOfAccessByUid(uid);
    }

    /**
     * 导出科考任务excel
     * @param response
     * @param request
     * @return
     */
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

    /**
     * 往excel写入数据
     * @param list
     * @return
     */
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

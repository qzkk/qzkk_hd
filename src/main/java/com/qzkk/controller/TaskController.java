package com.qzkk.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.qzkk.domain.Task;
import com.qzkk.domain.TaskForPageVO;
import com.qzkk.service.TaskService;
import com.qzkk.utils.ExcelUtil;
import com.qzkk.vo.ExcelOfTask;
import com.qzkk.vo.SelectTaskCondition;
import com.qzkk.vo.TeamVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
//    @PostMapping("/exportTask")
//    public void exportTask(@RequestBody String taskList) {
//        JsonObject jsonObject = (JsonObject) new JsonParser().parse(taskList);
//        JsonArray jsonArray= jsonObject.getAsJsonArray("taskList");
//        Gson gson=new Gson();
//        List<TaskForPageVO> taskForPageVO=new ArrayList<>();
//        for (JsonElement teamTaEle:jsonArray){
//            TaskForPageVO task=gson.fromJson(teamTaEle,new TypeToken<TaskForPageVO>(){}.getType());
//            taskForPageVO.add(task);
//        }
//        long t1=System.currentTimeMillis();
//        String filePath = "/Users/yangshuai/Desktop/csexcel/task"+t1+".xlsx";
//        ArrayList<ExcelOfTask> data = new ArrayList<>();
//        for (int i=0;i<taskForPageVO.size();i++){
//            ExcelOfTask excelOfTask = new ExcelOfTask();
//            excelOfTask.setSn(taskForPageVO.get(i).getSn());
//            excelOfTask.setDemand(taskForPageVO.get(i).getDemand());
//            excelOfTask.setEd(taskForPageVO.get(i).getEd());
//            excelOfTask.setName(taskForPageVO.get(i).getName());
//            excelOfTask.setRs(taskForPageVO.get(i).getRs());
//            excelOfTask.setSd(taskForPageVO.get(i).getSd());
//            excelOfTask.setSt(taskForPageVO.get(i).getSt());
//            switch(taskForPageVO.get(i).getState()){
//                case 0:
//                    excelOfTask.setState("待审核");
//                    break;
//                case 1:
//                    excelOfTask.setState("审核通过");
//                    break;
//                case -1:
//                    excelOfTask.setState("审核被拒绝");
//                    break;
//            }
//            data.add(excelOfTask);
//        }
//        ExcelUtil.writeWithTemplate(filePath,data);
//    }
    @PostMapping("/exportTask")
    public void exportTask(HttpServletResponse response,@RequestBody String taskList) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(taskList);
        JsonArray jsonArray= jsonObject.getAsJsonArray("taskList");
        Gson gson=new Gson();
        List<TaskForPageVO> taskForPageVO=new ArrayList<>();
        for (JsonElement teamTaEle:jsonArray){
            TaskForPageVO task=gson.fromJson(teamTaEle,new TypeToken<TaskForPageVO>(){}.getType());
            taskForPageVO.add(task);
        }
        long t1=System.currentTimeMillis();
        String filePath = "/Users/yangshuai/Desktop/csexcel/task1.xlsx";
        ArrayList<ExcelOfTask> excelList = new ArrayList<>();
        for (int i=0;i<taskForPageVO.size();i++){
            ExcelOfTask excelOfTask = new ExcelOfTask();
            excelOfTask.setSn(taskForPageVO.get(i).getSn());
            excelOfTask.setDemand(taskForPageVO.get(i).getDemand());
            excelOfTask.setEd(taskForPageVO.get(i).getEd());
            excelOfTask.setName(taskForPageVO.get(i).getName());
            excelOfTask.setRs(taskForPageVO.get(i).getRs());
            excelOfTask.setSd(taskForPageVO.get(i).getSd());
            excelOfTask.setSt(taskForPageVO.get(i).getSt());
            switch(taskForPageVO.get(i).getState()){
                case 0:
                    excelOfTask.setState("待审核");
                    break;
                case 1:
                    excelOfTask.setState("审核通过");
                    break;
                case -1:
                    excelOfTask.setState("审核被拒绝");
                    break;
            }
            excelList.add(excelOfTask);
        }
        //可以抽取为日期工具类
        Date date1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = df.format(date1);
        TemplateExportParams params = new TemplateExportParams(filePath, false);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("date", date);//导出一般都要日期
        data.put("list", excelList);//导出list集合
        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            export(response, book, "task");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * export导出请求头设置
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
        }
    }
}

package com.qzkk.vo;

import com.qzkk.utils.ExcelUtil;

import java.util.ArrayList;

public class A {
    public static void main(String[] args) {
        String filePath = "./测试.xlsx";
        ArrayList<ExcelOfTask> data = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            ExcelOfTask excelOfTask = new ExcelOfTask();
            excelOfTask.setSn("任务"+i);
            data.add(excelOfTask);
        }
        ExcelUtil.writeWithTemplate(filePath,data);
    }
}

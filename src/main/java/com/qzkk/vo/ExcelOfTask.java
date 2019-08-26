package com.qzkk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelOfTask extends BaseRowModel {
    @ExcelProperty(value = "任务名称", index = 0)
    private String sn;

    @ExcelProperty(value = "科考区域", index = 1)
    private String rs;

    @ExcelProperty(value = "任务内容", index = 2)
    private String st;

    @ExcelProperty(value = "服务保障需求", index = 3)
    private String demand;

    @ExcelProperty(value = "开始时间", index = 4)
    private String sd;

    @ExcelProperty(value = "结束时间", index = 5)
    private String ed;

    @ExcelProperty(value = "申请人", index = 6)
    private String name;

    @ExcelProperty(value = "审核状态", index = 7)
    private String state;
}

package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGoodApplyInfo {



    private String gId;
//    物资编号

    private String  gName;
//物资名称

    private String tName;
//    队伍名称

    private Integer number;
//    申请数量

    private String taName;
//    任务名称

    private String description;
//    申请理由

    private String applicationTime;
//    申请时间

    private BigInteger gaId;
//    申请编号


}

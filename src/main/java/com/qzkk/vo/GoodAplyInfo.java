package com.qzkk.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodAplyInfo {
    private BigInteger gaId;

    //物资编号
    private BigInteger gId;

    //申请人编号
    private BigInteger uId;

    //申请的物资数量
    private Integer number;

    //申请物资的描述
    private String description;

    //审核的状态：0待审核，1审核通过，-1审核不通过
    private Integer state;

    private String name;

    private String identifier;


}

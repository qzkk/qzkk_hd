package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskVO {
    private BigInteger id;
    private String demand;
    private String rs;
    private String sn;
    private String st;
    private String name;
    private Integer type;
    private Integer state;
}

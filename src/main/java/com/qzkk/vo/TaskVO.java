package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
    private String sd;
    private String ed;
    private String name;
    private Integer type;
    private Integer state;
    private String route;
}

package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.text.Bidi;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamList {
    private BigInteger tId;
//    小队编号

    private String tName;
//    小队名称

    private String taName;
//    任务名称

}



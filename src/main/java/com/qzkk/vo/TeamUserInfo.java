package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamUserInfo {
    private BigInteger uId;
    private String name ;
    private String position;
    private String unit;

}

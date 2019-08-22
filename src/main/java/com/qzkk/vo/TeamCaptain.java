package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCaptain {
    private BigInteger tid;
    private BigInteger uid;
    private String tname;
    private String uname;
}

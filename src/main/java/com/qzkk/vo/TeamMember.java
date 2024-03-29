package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember {

    private BigInteger id;

    private BigInteger uId;

    //真实姓名
    private String name;


    private String tname;


}

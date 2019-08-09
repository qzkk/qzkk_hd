package com.qzkk.vo;

import java.math.BigInteger;


public class TeamMember {
    private BigInteger uId;

    //真实姓名
    private String name;


    private String tname;


    public BigInteger getuId() {
        return uId;
    }

    public void setuId(BigInteger uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    public TeamMember(BigInteger uId, String name, String tname) {
        this.uId = uId;
        this.name = name;
        this.tname = tname;
    }

    public TeamMember() {
    }
}

package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamVO {
    private BigInteger tid;
    private String name;
    private String uname;
    private Integer state;
	public  BigInteger getTid() {
		return this.tid;
	}
}

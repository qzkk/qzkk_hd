package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodMessage {
    private String gname;
    private String tname ;
    private Integer state ;
    private Integer number;
    private String description;
    private String application_time;
    private String end_time;
}

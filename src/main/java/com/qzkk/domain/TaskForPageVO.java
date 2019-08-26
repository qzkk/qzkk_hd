package com.qzkk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
@Entity
@Table(name="TaskForPageVO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForPageVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}

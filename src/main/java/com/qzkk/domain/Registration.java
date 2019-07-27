package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Registration")
@Data
@NoArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rid;

    private String name;

    private int sex;

    private String age;

    private String identity;

    //民族
    private String nation;

    //文化程度
    private String educationDegree;

    private String homeAdress;

    private String email;

    private String phone;

    //登记日期
    private String rDate;
}

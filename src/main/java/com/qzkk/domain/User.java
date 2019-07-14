package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: jzc
 * @date: 13/7/2019-下午6:41
 * @description:
 */
@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uId;

    private String name;

    private String psd;

    private String account;

    private int sex;

    private String major;

    private String idCard;

    private int type;

    private int state;

    private int examine;
}

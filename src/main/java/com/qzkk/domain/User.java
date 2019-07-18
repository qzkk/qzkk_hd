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

    //真实姓名
    private String name;

    //密码（MD5加密）
    private String psd;

    //账号名
    private String account;

    private int sex;

    //专业
    private String major;

    //身份证号
    private String idCard;

    //类型：0队员 1队长 2管理员
    private int type;

    //科考状态
    private int state;

    //审核
    private int examine;

    //是否已经删除
    private int del;
}

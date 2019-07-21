package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: jzc
 * @date: 18/7/2019-下午8:52
 * @description:
 */
@Entity
@Table(name="team")
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tId;

    //队长编号
    private long uId;

    //小队人数
    private int number;

    //小队描述
    private String description;

    //小队名称
    private String name;

    //创建时间
    private Date createTime;

    //解散时间
    private Date delTime;

    //删除标志
    private int del = 0;

    //申请状态：0未审核，1审核通过，-1未通过
    private int state = 0;

}

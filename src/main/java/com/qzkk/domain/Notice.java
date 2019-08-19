package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 消息表
 */
@Entity
@Table(name="notice")
@Data
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //消息内容
    private String content;

    //广播的范围：0为给所有人广播，1为给某个小队广播
    private int noticeArea;

    //若noticeArea=1，则给某个小队广播
    private long teamId;
}

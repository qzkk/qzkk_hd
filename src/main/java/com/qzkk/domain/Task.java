package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 任务表
 */

@Entity
@Table(name="task")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //任务名称
    private String subjectName;

    //科考的任务内容
    private String subjectTask;

    //科考区域
    private String researchSite;

    //服务保障需求
    private String demand;

    //服务保障需求
    private String startDate;

    //服务保障需求
    private String endDate;

    //发布人
    private long uid;

    //审核的状态：0待审核，1审核通过，-1审核不通过
    private int state = 0;
}

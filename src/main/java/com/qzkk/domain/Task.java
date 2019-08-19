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

    //科考的主题
    private String subjectName;

    //科考的任务
    private String subjectTask;

    //科考区域
    private String researchSite;

    //服务保障需求
    private String demand;

    //来青日期
    private String comeDate;

    //离青时间
    private String backDate;

    //审核的状态：0待审核，1审核通过，-1审核不通过
    private int state = 0;
}

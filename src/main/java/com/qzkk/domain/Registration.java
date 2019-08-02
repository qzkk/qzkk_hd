package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 登记系统
 */

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

    private String castToSex;

    //职位
    private String workPosition;

    //工作单位
    private String workUnit;

    //专题名称
    private String subject;

    //科考地点
    private String researchSite;

    //服务保障需求
    private String demand;

    //来青日期
    private String comeDate;

    //离青时间
    private String backDate;

    //需要第几页，只是为了传输方便才加的字段，对于实际数据没有任何意义
    private Integer pageOffset;

    //一页多少条，只是为了传输方便才加的字段，对于实际数据没有任何意义
    private Integer pageSize;
}

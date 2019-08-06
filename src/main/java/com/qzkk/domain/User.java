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

    //密码（MD5加密）
    private String psd;

    //账号名
    private String account;

    //真实姓名
    private String name;

    //性别
    private int sex;

    //职位
    private String workPosition;

    //工作单位
    private String workUnit;

    //专题主题
    private String subjectName;

    //科考区域
    private String researchSite;

    //服务保障需求
    private String demand;

    //来青日期
    private String comeDate;

    //离青时间
    private String backDate;

    //身份证号
    private String idCard;

    //类型：0队员 1队长 2管理员
    private int type;

    //科考状态（暂时功能用不上，先留着吧）
    private int state;

    //审核
    private int examine;

    //是否已经删除
    private int del;

    //所在小队编号
    private long tId;

    //用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
    private Integer pageOffset;

    //用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
    private Integer pageSize;
}

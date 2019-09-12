package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "return_application")
@Data
@NoArgsConstructor
public class ReturnApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //归还id，主键
    private int id;
    //归还的物品id
    private long goodsId;
    //归还的物品名称
    private String goodsName;
    //归还物品的物资编码
    private String identifier;
    //归还的数量
    private String retrunNumber;
    //归还的小队id
    private long teamId;
    //归还的小队名称
    private String teamName;
    //del=1代表删除，del=0代表未删除
    private int del=0;
    //物资申请id
    private long gaId;
}

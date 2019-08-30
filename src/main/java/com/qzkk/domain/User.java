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
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long uId;

        // 为了添加成员的功能暂时先用着，其实没有实际意义
        private long tId;

        // 密码（MD5加密）
        private String psd;

        // 账号名
        private String account;

        // 真实姓名
        private String name;

        // 性别
        private int sex;

        // 职位
        private String workPosition;

        // 工作单位
        private String workUnit;

        // 身份证号
        private String idCard;

        // 类型：0队员 1队长 2管理员
        private int type;

        // 科考状态（暂时功能用不上，先留着吧）
        private int state;

        // 审核
        private int examine;

        // 是否已经删除
        private int del;

        // 用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
        private Integer pageOffset;

        // 用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
        private Integer pageSize;

        // 来青日期
        private String comeDate;

        // 离青时间
        private String backDate;

        public void setDel(int i) {
                this.del = i;
        }

        public int getPageOffset() {
                return this.pageOffset;
        }

        public int getPageSize() {
                return this.pageSize;
        }

        public String getName() {
                return this.name;
        }

        public String getWorkUnit() {
                return this.workUnit;
        }

        public long getTId() {
                return this.tId;
        }

        public long getUId() {
                return this.uId;
        }

        public String getAccount() {
                return this.account;
        }

        public String getPsd() {
                return this.psd;
        }

        public void setPsd(String password) {
                this.psd = password;
        }

        public void setState(int i) {
                this.state = i;
        }

        public void setExamine(int i) {
                this.examine = i;
        }

        public int getExamine() {
                return this.examine;
        }

        public void setPageOffset(int i) {
                this.pageOffset = i;
        }

        public void setPageSize(int i) {
                this.pageSize = i;
        }
}

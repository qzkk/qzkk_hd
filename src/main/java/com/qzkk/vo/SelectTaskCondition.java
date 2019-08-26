package com.qzkk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectTaskCondition {
    private String tname;
    private String uname;
    private String state;
    //用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
    private Integer pageOffset;

    //用于分页，对于实际数据没有作用，就是传输数据的时候方便一些
    private Integer pageSize;
}

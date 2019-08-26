package com.qzkk.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String name;
    private Integer type;
    private Integer sex;
    private String idCard;
    private String wp;
    private String wu;
}

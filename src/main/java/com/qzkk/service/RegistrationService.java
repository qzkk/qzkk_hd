package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegistrationService {
    void save(User registration);

    /**
     * 查询所有数据
     * @return
     */
    List<User> selectall();

    /**
     * 没有约束条件的情况下，对全部数据进行分页
     * @param Offset 偏移量--就是第几页的数据
     * @param size  一页多少条
     * @return
     */
    Page<User> findAllToPage(Integer Offset,Integer size);

    /**
     * 没有约束条件的情况下，对全部数据进行分页，适用于固定约束条件(只有一个条件)。
     * 如果想要动态添加约束条件则需要用到selectToPageByDynamic方法
     * @param registration
     * @return
     */
    Page<User> selectToPageByStatic(User registration);

    /**
     * 如果有约束条件，并且进行分页
     * 可以动态添加约束条件，并进行分页
     * @param registration
     * @return
     */

    JSONObject selectToPageByDynamic(User registration);
}

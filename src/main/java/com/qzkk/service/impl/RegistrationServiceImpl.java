package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.RegistrationRepository;
import com.qzkk.domain.User;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Override
    public void save(User registration) {
       registrationRepository.save(registration);
    }

    @Override
    public List<User> selectall() {
        return registrationRepository.findAll();
    }

    @Override
    public Page<User> selectToPageByStatic(User registration) {

        Pageable pageable = PageRequest.of(registration.getPageOffset(),registration.getPageSize(), Sort.Direction.ASC, "u_id");
        Page<User> registrations=registrationRepository.findToPage(registration.getName(),pageable);
        return registrations;
    }

    @Override
    public Page<User> selectToPageByStatic1(long tid, int pageOffset) {
        Pageable pageable = PageRequest.of(pageOffset,10, Sort.Direction.ASC, "u_id");
        Page<User> registrations=registrationRepository.findToPage1(tid,pageable);
        return registrations;
    }

    @Override
    public Page<User> selectToPageByDynamic1(long tid, int pageOffset, String name) {
        Pageable pageable = PageRequest.of(pageOffset,10, Sort.Direction.ASC, "u_id");
        Page<User> registrations=registrationRepository.selectToPageByDynamic1(tid,name,pageable);
        return registrations;
    }

    @Override
    public Page<User> findAllToPage(Integer Offset,Integer size) {

        Page<User> registrations = registrationRepository.findAll(new PageRequest(Offset, size));
        return registrations;
    }

    @Override
    public JSONObject selectToPageByDynamic(User registration) {
        JSONObject resData=new JSONObject();
        Pageable pageable = PageRequest.of(registration.getPageOffset(),registration.getPageSize(),
                Sort.Direction.ASC, "rid");
        StringBuffer dataSql = new StringBuffer("select * from user a where a.del=0 and a.examine=1");
        StringBuffer countSql = new StringBuffer("select count(1) from user a where a.del=0 and a.examine=1");
        //进行动态添加约束
        if (!registration.getName().isEmpty()){
            dataSql.append(" and a.name like CONCAT('%',:name,'%')");
            countSql.append(" and a.name like CONCAT('%',:name,'%')");
        }
        if (!registration.getWorkUnit().isEmpty()){
            dataSql.append(" and a.work_unit like CONCAT('%',:workUnit,'%')");
            countSql.append(" and a.work_unit like CONCAT('%',:workUnit,'%')");
        }
        dataSql.append(" order by a.u_id desc");
        //创建本地sql查询实例
        Query dataQuery = (Query) entityManager.createNativeQuery(dataSql.toString(), User.class);
        //查询总共有多少条数据，用于前端分页
        Query countQuery = entityManager.createNativeQuery(countSql.toString());

        //设置参数
        if (!registration.getName().isEmpty()) {
            dataQuery.setParameter("name", registration.getName());
            countQuery.setParameter("name", registration.getName());
        }
        if (!registration.getWorkUnit().isEmpty()) {
            dataQuery.setParameter("workUnit", registration.getWorkUnit());
            countQuery.setParameter("workUnit", registration.getWorkUnit());
        }

        dataQuery.setFirstResult((int) pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        BigInteger count = (BigInteger) countQuery.getSingleResult();
        Long totalNum = count.longValue();
        List<User> list=dataQuery.getResultList();
        resData.put("totalNum",totalNum);
        resData.put("list",list);
        return resData;
    }
}

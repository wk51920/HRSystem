package com.wk51920.hrsystem.dao.impl;

import com.wk51920.common.dao.impl.BaseDaoHibernate4;
import com.wk51920.hrsystem.dao.ApplicationDao;
import com.wk51920.hrsystem.domain.Application;
import com.wk51920.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
// 桥接模式
public class ApplicationDaoHibernate4 extends BaseDaoHibernate4<Application> implements ApplicationDao {
    @Override
    public List<Application> findByEmp(Employee emp) {
        return find("select a from Application as a where "
        +"a.attend.employee=?0", emp);
    }
}

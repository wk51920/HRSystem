package com.wk51920.hrsystem.dao;

import com.wk51920.common.dao.BaseDao;
import com.wk51920.hrsystem.domain.Application;
import com.wk51920.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
// 具体DAO的实现使用桥接模式
public interface ApplicationDao extends BaseDao<Application> {
    // 根据员工查询未处理的异动申请
    List<Application> findByEmp(Employee emp);
}

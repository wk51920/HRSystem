package com.wk51920.hrsystem.dao;

import com.wk51920.common.dao.BaseDao;
import com.wk51920.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public interface EmployeeDao extends BaseDao<Employee> {
    // 根据用户名和密码查询员工
    List<Employee> findByNameAndPass(Employee emp);

    // 根据用户名查询员工
    Employee findByName(String name);
}

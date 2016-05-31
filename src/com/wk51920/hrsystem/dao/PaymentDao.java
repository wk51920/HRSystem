package com.wk51920.hrsystem.dao;

import com.wk51920.common.dao.BaseDao;
import com.wk51920.hrsystem.domain.Employee;
import com.wk51920.hrsystem.domain.Payment;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public interface PaymentDao extends BaseDao<Payment> {
    // 根据员工查询月结薪水
    List<Payment> findByEmp(Employee emp);

    // 根据员工和月份来查询月结薪水
    Payment findByMonthAndEmp(String payMonth, Employee emp);
}

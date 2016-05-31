package com.wk51920.hrsystem.dao.impl;

import com.wk51920.common.dao.impl.BaseDaoHibernate4;
import com.wk51920.hrsystem.dao.PaymentDao;
import com.wk51920.hrsystem.domain.Employee;
import com.wk51920.hrsystem.domain.Payment;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class PaymentDaoHibernate4 extends BaseDaoHibernate4<Payment> implements PaymentDao {
    @Override
    public List<Payment> findByEmp(Employee emp) {
        return find("select p from Payment as p where p.employee=?0", emp);
    }

    @Override
    public Payment findByMonthAndEmp(String payMonth, Employee emp) {
        List<Payment> pays = find("select p from Payment as p where"
                + " p.employee=?0 and p.payMonth=?1", emp, payMonth);
        if (pays != null && pays.size() > 0)
            return pays.get(0);
        return null;
    }
}

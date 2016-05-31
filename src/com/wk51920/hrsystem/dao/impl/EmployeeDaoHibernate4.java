package com.wk51920.hrsystem.dao.impl;

import com.wk51920.common.dao.impl.BaseDaoHibernate4;
import com.wk51920.hrsystem.dao.EmployeeDao;
import com.wk51920.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class EmployeeDaoHibernate4 extends BaseDaoHibernate4<Employee> implements EmployeeDao {
    // 此处返回List纯属,带占位符的find方法只能返回List类型
    @Override
    public List<Employee> findByNameAndPass(Employee emp) {
        return find("select p from Employee p where p.name = ?0 and p.pass = ?1",
                emp.getName(), emp.getPass());
    }

    @Override
    public Employee findByName(String name) {
        List<Employee> emps = find("select e from Employee e where e.name = ?0", name);
        if(emps!=null && emps.size()>=1)
            return emps.get(0);
        return null;
    }
}

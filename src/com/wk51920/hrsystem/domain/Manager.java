package com.wk51920.hrsystem.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wk51920 on 16/5/28.
 */
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorValue(value="2")
public class Manager
        extends Employee implements Serializable
{
    private static final long serialVersionUID = 48L;
    // 该经理管理的部门
    @Column(name="dept_name", length=50)
    private String dept;
    // 该经理对应的所有员工
    @OneToMany(targetEntity=Employee.class, mappedBy="manager")
    private Set<Employee> employees = new HashSet<Employee>();
    // 该经理签署的所有批

    @OneToMany(targetEntity=CheckBack.class , mappedBy="manager")
    private Set<CheckBack> checks = new HashSet<CheckBack>();

    // 无参数的构造器
    public Manager()
    {
    }
    // 初始化全部成员变量的构造器
    public Manager(String dept , Set<Employee> employees
            , Set<CheckBack> checks)
    {
        this.dept = dept;
        this.employees = employees;
        this.checks = checks;
    }

    // dept的setter和getter方法
    public void setDept(String dept)
    {
        this.dept = dept;
    }
    public String getDept()
    {
        return this.dept;
    }

    // employees的setter和getter方法
    public void setEmployees(Set<Employee> employees)
    {
        this.employees = employees;
    }
    public Set<Employee> getEmployees()
    {
        return this.employees;
    }

    // checks的setter和getter方法
    public void setChecks(Set<CheckBack> checks)
    {
        this.checks = checks;
    }
    public Set<CheckBack> getChecks()
    {
        return this.checks;
    }
}
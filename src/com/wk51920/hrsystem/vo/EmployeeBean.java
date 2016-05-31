package com.wk51920.hrsystem.vo;

import java.io.Serializable;

/**
 * Created by wk51920 on 16/5/30.
 */
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 48L;

    private String empName;
    private String empPass;
    private double amount;

    public EmployeeBean() {
    }

    public EmployeeBean(String empName, String empPass, double amount) {
        this.empName = empName;
        this.empPass = empPass;
        this.amount = amount;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpPass() {
        return empPass;
    }

    public double getAmount() {
        return amount;
    }
}

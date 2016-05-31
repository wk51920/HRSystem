package com.wk51920.hrsystem.vo;

import java.io.Serializable;

/**
 * Created by wk51920 on 16/5/30.
 */
public class SalaryBean implements Serializable {
    private final static long serialVersionUID = 48L;

    private String empName;
    private double amount;

    public SalaryBean() {
    }

    public SalaryBean(String empName, double amount) {
        this.empName = empName;
        this.amount = amount;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public double getAmount() {
        return amount;
    }
}

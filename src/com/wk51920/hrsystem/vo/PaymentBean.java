package com.wk51920.hrsystem.vo;

import java.io.Serializable;

/**
 * Created by wk51920 on 16/5/30.
 */
public class PaymentBean implements Serializable{
    private static final long serialVersionUID = 48L;

    private String payMonth;
    private double amount;

    public PaymentBean() {
    }

    public PaymentBean(String payMonth, double amount) {
        this.payMonth = payMonth;
        this.amount = amount;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public double getAmount() {
        return amount;
    }
}

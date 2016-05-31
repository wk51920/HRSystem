package com.wk51920.hrsystem.vo;

import java.io.Serializable;

/**
 * Created by wk51920 on 16/5/30.
 */
public class AppBean implements Serializable {
    private static final long serialVersionUID = 48L;

    private int id;
    private String emp;
    private String unAttend;
    private String toAttend;
    private String reason;

    public AppBean() {
    }

    public AppBean(int id, String emp, String unAttend, String toAttend, String reason) {
        this.id = id;
        this.emp = emp;
        this.unAttend = unAttend;
        this.toAttend = toAttend;
        this.reason = reason;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getEmp() {
        return emp;
    }

    public String getUnAttend() {
        return unAttend;
    }

    public String getToAttend() {
        return toAttend;
    }

    public String getReason() {
        return reason;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public void setUnAttend(String unAttend) {
        this.unAttend = unAttend;
    }

    public void setToAttend(String toAttend) {
        this.toAttend = toAttend;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

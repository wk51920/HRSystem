package com.wk51920.hrsystem.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wk51920 on 16/5/30.
 */
public class AttendBean implements Serializable {
    private static final long serialVersionUID = 48L;

    private int id;
    private String dutyDay;
    private String unType;
    private Date time;

    public AttendBean() {
    }

    public AttendBean(int id, String dutyDay, String unType, Date time) {
        this.id = id;
        this.dutyDay = dutyDay;
        this.unType = unType;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public void setUnType(String unType) {
        this.unType = unType;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public String getUnType() {
        return unType;
    }

    public Date getTime() {
        return time;
    }
}

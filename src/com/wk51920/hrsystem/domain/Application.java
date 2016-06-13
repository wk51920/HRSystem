package com.wk51920.hrsystem.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wk51920 on 16/5/28.
 */
@Entity
@Table(name="application_inf")
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Application
        implements Serializable
{
    private static final long serialVersionUID = 48L;

    // 代表标识属性
    @Id	@Column(name="app_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // 申请理由
    @Column(name="app_reason" , length=50)
    private String reason;
    // 是否处理
    @Column(name="app_result")
    private boolean result;
    // 关联的出勤记录
    @ManyToOne(targetEntity=Attend.class)
    @JoinColumn(name="attend_id" , nullable=false)
    private Attend attend;

    // 希望将指定出勤改为的type类型
    @ManyToOne(targetEntity=AttendType.class)
    @JoinColumn(name="type_id", nullable=false)
    private AttendType type;
    // 申请的结果
    @OneToOne(targetEntity=CheckBack.class, mappedBy="app")
    private CheckBack check;

    //无参数的构造器
    public Application()
    {
    }
    // 初始化全部成员变量的构造器
    public Application(Integer id , String reason ,
                       boolean result , Attend attend ,
                       AttendType type , CheckBack check)
    {
        this.id = id;
        this.reason = reason;
        this.result = result;
        this.attend = attend;
        this.type = type;
        this.check = check;
    }

    // id的setter和getter方法
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getId()
    {
        return this.id;
    }

    // reason的setter和getter方法
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    public String getReason()
    {
        return this.reason;
    }

    // result的setter和getter方法
    public void setResult(boolean result)
    {
        this.result = result;
    }
    public boolean getResult()
    {
        return this.result;
    }

    // attend的setter和getter方法
    public void setAttend(Attend attend)
    {
        this.attend = attend;
    }
    public Attend getAttend()
    {
        return this.attend;
    }

    // type的setter和getter方法
    public void setType(AttendType type)
    {
        this.type = type;
    }
    public AttendType getType()
    {
        return this.type;
    }

    // check的setter和getter方法
    public void setCheck(CheckBack check)
    {
        this.check = check;
    }
    public CheckBack getCheck()
    {
        return this.check;
    }

}
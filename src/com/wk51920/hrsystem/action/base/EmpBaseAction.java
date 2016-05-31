package com.wk51920.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.wk51920.hrsystem.service.EmpManager;

/**
 * Created by wk51920 on 16/5/30.
 */
public class EmpBaseAction extends ActionSupport {
    // 依赖的业务逻辑组件
    protected EmpManager empManager;

    public void setEmpManager(EmpManager empManager) {
        this.empManager = empManager;
    }


}

package com.wk51920.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.wk51920.hrsystem.service.MgrManager;

/**
 * Created by wk51920 on 16/5/30.
 */
public class MgrBaseAction extends ActionSupport {
    protected MgrManager mgrManager;

    public void setMgrManager(MgrManager mgrManager) {
        this.mgrManager = mgrManager;
    }
}

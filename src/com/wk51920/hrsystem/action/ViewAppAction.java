package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.MgrBaseAction;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ViewAppAction extends MgrBaseAction {
    private List apps;

    public void setApps(List apps) {
        this.apps = apps;
    }

    public List getApps() {

        return apps;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // 获取需要被当前经理处理的全部申请
        setApps(mgrManager.getAppsByMgr(mgrName));
        return SUCCESS;
    }
}

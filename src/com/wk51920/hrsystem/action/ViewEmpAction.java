package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.MgrBaseAction;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ViewEmpAction extends MgrBaseAction {
    // 封装当前经理所有员工的list
    private List emps;

    public void setEmps(List emps) {
        this.emps = emps;
    }

    public List getEmps() {

        return emps;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        setEmps(mgrManager.getEmpsByMgr(mgrName));
        return SUCCESS;
    }
}

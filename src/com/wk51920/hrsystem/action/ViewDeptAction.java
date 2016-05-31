package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.MgrBaseAction;
import com.wk51920.hrsystem.vo.SalaryBean;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ViewDeptAction extends MgrBaseAction {
    // 封装发薪列表的List成员变量
    private List sals;

    public void setSals(List sals) {
        this.sals = sals;
    }

    public List getSals() {

        return sals;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);

        // 调用业务逻辑方法取得当前员工的全部发薪列表
        List<SalaryBean> result = mgrManager.getSalaryByMgr(mgrName);
        setSals(result);
        return SUCCESS;
    }
}

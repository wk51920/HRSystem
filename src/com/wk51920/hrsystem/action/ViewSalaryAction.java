package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.EmpBaseAction;
import com.wk51920.hrsystem.vo.PaymentBean;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ViewSalaryAction extends EmpBaseAction {
    // 封装所有发薪信息的List
    private List salarys;

    public void setSalarys(List salarys) {
        this.salarys = salarys;
    }

    public List getSalarys() {

        return salarys;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String user = (String) ctx.getSession().get(WebConstant.USER);
        List<PaymentBean> salarys = empManager.empSalary(user);
        setSalarys(salarys);
        return SUCCESS;
    }
}

package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.MgrBaseAction;
import com.wk51920.hrsystem.domain.Employee;


/**
 * Created by wk51920 on 16/5/30.
 */
public class AddEmpAction extends MgrBaseAction {
    // 代表新增员工的成员变量
    private Employee emp;

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getEmp() {

        return emp;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // 添加新用户
        mgrManager.addEmp(emp, mgrName);
        addActionMessage("新增员工成功");
        return SUCCESS;
    }
}

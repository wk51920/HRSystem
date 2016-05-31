package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.EmpBaseAction;
import com.wk51920.hrsystem.vo.AttendBean;

import java.util.List;

/**
 * Created by wk51920 on 16/5/31.
 */
public class ViewUnAttendAction extends EmpBaseAction {
    private List<AttendBean> unAttend;

    public void setUnAttend(List<AttendBean> unAttend) {
        this.unAttend = unAttend;
    }

    public List<AttendBean> getUnAttend() {

        return unAttend;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String user = (String)ctx.getSession().get(WebConstant.USER);
        List<AttendBean> result = empManager.unAttend(user);
        setUnAttend(result);
        return SUCCESS;
    }
}

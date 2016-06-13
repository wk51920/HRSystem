package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.EmpBaseAction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wk51920 on 16/5/30.
 */
public class PunchAction extends EmpBaseAction {
    // 封装处理结果的punchIsValid成员变量
    private int punchIsValid;

    public void setPunchIsValid(int punchIsValid) {
        this.punchIsValid = punchIsValid;
    }

    public int getPunchIsValid() {
        return punchIsValid;

    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String user = (String) ctx.getSession().get(WebConstant.USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 格式化当前时间
        String dutyDay = sdf.format(new Date());
        // 调用业务逻辑方法处理用户请求
        int result = empManager.validPunch(user, dutyDay);
        System.out.println(result);
        setPunchIsValid(result);
        return SUCCESS;
    }
}

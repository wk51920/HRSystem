package com.wk51920.hrsystem.action.authority;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wk51920.hrsystem.action.WebConstant;

/**
 * Created by wk51920 on 16/5/31.
 */
public class MgrAuthorityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String level = (String) ctx.getSession().get(WebConstant.LEVEL);
        if (level != null && level.equals(WebConstant.MGR_LEVEL)) {
            return actionInvocation.invoke();
        }
        return Action.LOGIN;
    }
}

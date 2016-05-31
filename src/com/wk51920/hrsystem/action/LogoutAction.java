package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wk51920 on 16/5/30.
 */
public class LogoutAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        // 使该请求对应的session失效
        session.invalidate();
        return SUCCESS;
    }
}

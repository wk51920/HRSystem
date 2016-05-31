package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.EmpBaseAction;
import com.wk51920.hrsystem.domain.Manager;

import static com.wk51920.hrsystem.service.EmpManager.*;

/**
 * Created by wk51920 on 16/5/30.
 */
public class LoginAction extends EmpBaseAction {
    // 定义一个常量作为员工登陆成功的Result名
    private final String EMP_RESULT = "emp";
    // 经理登陆成功
    private final String MGR_RESULT = "mgr";
    // 封装请求参数
    private Manager manager;
    // 登陆的验证码
    private String vercode;

    public Manager getManager() {
        return manager;
    }

    public String getVercode() {
        return vercode;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String ver2 = (String)ctx.getSession().get("rand");
        System.out.println(vercode+" "+manager.getName()+" "+manager.getPass());
        if(vercode.equalsIgnoreCase(ver2)){
            // 调用业务逻辑方法来处理登录请求
            int result = empManager.validLogin(getManager());
            // 登陆结果为普通员工
            if(result == LOGIN_EMP){
                ctx.getSession().put(WebConstant.USER,manager.getName());
                ctx.getSession().put(WebConstant.LEVEL, WebConstant.EMP_LEVEL);
                addActionMessage("您已经成功登陆系统");
                return EMP_RESULT;
            }
            // 登陆结果为经理
            else if(result==LOGIN_MGR){
                ctx.getSession().put(WebConstant.USER, manager.getName());
                ctx.getSession().put(WebConstant.LEVEL, WebConstant.MGR_LEVEL);
                addActionMessage("您已经成功登陆系统");
                return MGR_RESULT;
            }// 用户名和密码不匹配
            else{
                addActionMessage("用户名/密码不匹配");
                return ERROR;
            }
        }
        // 验证码不匹配
        addActionMessage("验证码不匹配,请重新输入");
        return ERROR;
    }
}

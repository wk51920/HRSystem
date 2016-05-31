package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wk51920.hrsystem.service.EmpManager;

import java.util.Date;

import static com.wk51920.hrsystem.service.EmpManager.*;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ProcessPunchAction extends ActionSupport {
    // 该Action所依赖的业务逻辑组件
    private EmpManager empMgr;

    public void setEmpMgr(EmpManager empMgr) {
        this.empMgr = empMgr;
    }

    // 处理上班打卡的方法
    public String come() throws Exception {
        return process(true);
    }

    //处理下班打卡
    public String leave() throws Exception {
        return process(false);
    }

    private String process(boolean isCome) throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        String user = (String) ctx.getSession().get(WebConstant.USER);
        System.out.println("----打卡----" + user);
        String dutyDay = new Date(System.currentTimeMillis()).toString();

        // 调用业务逻辑方法处理打卡请求
        int result = empMgr.punch(user, dutyDay, isCome);
        switch (result) {
            case PUNCH_FAIL:
                addActionMessage("打卡失败");
                break;
            case PUNCHED:
                addActionMessage("您已经打过卡了,不要重复打卡");
                break;
            case PUNCH_SUCC:
                addActionMessage("打卡成功");
                break;
        }
        return SUCCESS;
    }
}

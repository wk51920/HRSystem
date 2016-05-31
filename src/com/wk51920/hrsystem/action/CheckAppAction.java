package com.wk51920.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import com.wk51920.hrsystem.action.base.MgrBaseAction;

/**
 * Created by wk51920 on 16/5/30.
 */
public class CheckAppAction extends MgrBaseAction {
    // 需要被处理的申请ID
    private int appid;
    // 封装处理结果
    private String result;

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAppid() {

        return appid;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String mgrName = (String)ctx.getSession().get(WebConstant.USER);

        // 通过申请
        if(result.equals("pass")){
            mgrManager.check(appid, mgrName, true);
        }
        // 拒绝申请
        else if(result.equals("deny")){
            mgrManager.check(appid,mgrName,false);
        }else
            throw new Exception("参数丢失");
        return SUCCESS;
    }
}

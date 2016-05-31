package com.wk51920.hrsystem.action;

import com.wk51920.hrsystem.action.base.EmpBaseAction;
import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class AppChangeAction extends EmpBaseAction {
    // 封装所有异动的列表
    private List types;

    public void setTypes(List types) {
        this.types = types;
    }

    public List getTypes() {

        return types;
    }

    // 处理用户请求
    public String execute() throws Exception{
        setTypes(empManager.getAllType());
        return SUCCESS;
    }
}

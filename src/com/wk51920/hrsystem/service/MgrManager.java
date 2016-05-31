package com.wk51920.hrsystem.service;


import com.wk51920.hrsystem.domain.Employee;
import com.wk51920.hrsystem.exception.HrException;
import com.wk51920.hrsystem.vo.AppBean;
import com.wk51920.hrsystem.vo.EmployeeBean;
import com.wk51920.hrsystem.vo.SalaryBean;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public interface MgrManager {
    // 新增员工
    void addEmp(Employee emp, String mgr) throws HrException;

    // 根据经理返回所有的部门上个月工资
    List<SalaryBean> getSalaryByMgr(String mgr);

    // 根据经理返回该部门的全部员工
    List<EmployeeBean> getEmpsByMgr(String mgr);

    // 根据经理返回该部门的没有批复的申请
    List<AppBean> getAppsByMgr(String mgr);

    // 处理申请
    void check(int appid, String mgrName, boolean result);

}

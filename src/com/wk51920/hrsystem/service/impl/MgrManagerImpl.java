package com.wk51920.hrsystem.service.impl;


import com.wk51920.hrsystem.dao.*;
import com.wk51920.hrsystem.domain.*;
import com.wk51920.hrsystem.exception.HrException;
import com.wk51920.hrsystem.service.MgrManager;
import com.wk51920.hrsystem.vo.AppBean;
import com.wk51920.hrsystem.vo.EmployeeBean;
import com.wk51920.hrsystem.vo.SalaryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by wk51920 on 16/5/30.
 */
public class MgrManagerImpl implements MgrManager {
    // Spring注入依赖所有DAO组件
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;
    private PaymentDao payDao;

    public void setAppDao(ApplicationDao appDao) {
        this.appDao = appDao;
    }

    public void setAttendDao(AttendDao attendDao) {
        this.attendDao = attendDao;
    }

    public void setTypeDao(AttendTypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public void setCheckDao(CheckBackDao checkDao) {
        this.checkDao = checkDao;
    }

    public void setEmpDao(EmployeeDao empDao) {
        this.empDao = empDao;
    }

    public void setMgrDao(ManagerDao mgrDao) {
        this.mgrDao = mgrDao;
    }

    public void setPayDao(PaymentDao payDao) {
        this.payDao = payDao;
    }

    // 给该经理新增员工
    @Override
    public void addEmp(Employee emp, String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        if (m == null)
            throw new HrException("您是经历吗? 活腻还未登陆?");
        emp.setManager(m);
        empDao.save(emp);
    }

    // 根据经理返回所在的部门每个员工上个月工资
    @Override
    public List<SalaryBean> getSalaryByMgr(String mgr) {
        Manager m = mgrDao.findByName(mgr);
        if (m == null)
            throw new HrException("您是经理吗? 或您还未登陆?");
        // 查询该经理对应的
        Set<Employee> emps = m.getEmployees();
        if (emps == null || emps.size() < 1)
            throw new HrException("你的部门没有员工");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());
        List<SalaryBean> result = new ArrayList<SalaryBean>();
        for (Employee e : emps) {
            Payment p = payDao.findByMonthAndEmp(payMonth, e);
            if (p != null)
                result.add(new SalaryBean(e.getName(), p.getAmount()));
        }
        return result;
    }

    // 根据经理返回部门的全部员工
    @Override
    public List<EmployeeBean> getEmpsByMgr(String mgr) {
        Manager m = mgrDao.findByName(mgr);
        if (m == null)
            throw new HrException("您是经理吗? 或您还未登陆?");
        Set<Employee> emps = m.getEmployees();
        if (emps == null || emps.size() < 1)
            throw new HrException("您的部门没有员工");
        // 封装VO
        List<EmployeeBean> result = new ArrayList<EmployeeBean>();
        for (Employee e : emps) {
            result.add(new EmployeeBean(e.getName(), e.getPass(), e.getSalary()));
        }
        return result;
    }

    // 根据经理返回该部门的没有批复的申请
    @Override
    public List<AppBean> getAppsByMgr(String mgr) {
        Manager m = mgrDao.findByName(mgr);
        if (m == null)
            throw new HrException("您是经理吗? 或您还未登陆?");
        Set<Employee> emps = m.getEmployees();
        if (emps == null || emps.size() < 1)
            throw new HrException("您的部门没有员工");
        // 封装VO
        List<AppBean> result = new ArrayList<AppBean>();
        for (Employee e : emps) {
            // 查看某员工的所有申请
            List<Application> apps = appDao.findByEmp(e);
            if (apps != null && apps.size() > 0) {
                for (Application app : apps) {
                    // 中选择还未处理的申请
                    if (app.getResult() == false) {
                        Attend attend = app.getAttend();
                        result.add(new AppBean(app.getId(), e.getName(), attend.getType().getName(),
                                app.getType().getName(), app.getReason()));
                    }
                }
            }
        }

        return result;
    }

    // 处理申请
    @Override
    public void check(int appid, String mgrName, boolean result) {
        Application app = appDao.get(Application.class, appid);
        CheckBack check = new CheckBack();
        check.setApp(app);
        Manager manager = mgrDao.findByName(mgrName);
        if (manager == null)
            throw new HrException("您是经理吗? 或您还未登陆?");
        check.setManager(manager);
        // 同意通过申请
        if (result) {
            // 设置通过申请
            check.setResult(true);

            // 修改申请为已批复
            app.setResult(true);
            appDao.update(app);
            // 为真时,还需要修改出勤的类型
            Attend attend = app.getAttend();
            attend.setType(app.getType());
            attendDao.update(attend);
        } else {
            // 没有通过申请
            check.setResult(false);
            app.setResult(true);
            appDao.update(app);
        }
        // 保存申请批复
        checkDao.save(check);
    }
}

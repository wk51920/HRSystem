package com.wk51920.hrsystem.service.impl;

import com.sun.corba.se.impl.orbutil.concurrent.SyncUtil;
import com.wk51920.hrsystem.dao.*;
import com.wk51920.hrsystem.domain.*;
import com.wk51920.hrsystem.service.EmpManager;
import com.wk51920.hrsystem.vo.AttendBean;
import com.wk51920.hrsystem.vo.PaymentBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class EmpManagerImpl implements EmpManager {
    // Spring依赖注入所有DAO组件
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

    // 以经理身份来验证登陆
    @Override
    public int validLogin(Manager mgr) {
        if (mgrDao.findByNameAndPass(mgr).size() >= 1)
            return LOGIN_MGR;
        else if (empDao.findByNameAndPass(mgr).size() >= 1)
            return LOGIN_EMP;
        else
            return LOGIN_FAIL;
    }

    // 自动打卡,周一到周五,早上7:00为每个员工插入旷工记录
    @Override
    public void autoPunch() {
        System.out.println("自动插入旷工记录");
        List<Employee> emps = empDao.findAll(Employee.class);
        // 获取当前时间
        String dutyDay = new Date(System.currentTimeMillis()).toString();
        for (Employee e : emps) {
            // 获取旷工对应的出勤类型
            AttendType atype = typeDao.get(AttendType.class, 6);
            Attend a = new Attend();
            a.setDutyDay(dutyDay);
            a.setType(atype);

            // 如果当前时间是早上,对应于上班打卡
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT)
                a.setIsCome(true); // 上班打卡
            else
                a.setIsCome(false); // 下班打卡

            a.setEmployee(e);
            attendDao.save(a);
        }
    }

    // 自动结算工资, 每月1号,结算上个月工资
    @Override
    public void autoPay() {
        System.out.println("自动插入工资结算");
        List<Employee> emps = empDao.findAll(Employee.class);
        // 获取上个月时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());

        // 为每个员工计算上个月工资
        for (Employee e :
                emps) {
            Payment pay = new Payment();
            // 获取该员工的工资
            double amount = e.getSalary();
            // 获取该员工上个月的出勤记录
            List<Attend> attends = attendDao.findByEmpAndMonth(e, payMonth);

            // 用工资累积其出勤记录的工资
            for (Attend a : attends) {
                amount += a.getType().getAmerce();
            }

            // 添加工资结算
            pay.setEmployee(e);
            pay.setAmount(amount);
            pay.setPayMonth(payMonth);
            payDao.save(pay);
        }
    }

    // 验证某个员工是否可以打卡
    @Override
    public int validPunch(String user, String duytDay) {
        Employee emp = empDao.findByName(user);
        if (emp == null)
            return NO_PUNCH;
        List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, duytDay);
        // 系统没有为该用户当天插入空打卡记录,无法打卡
        if (attends == null || attends.size() <= 0)
            return NO_PUNCH;
            // 开始上班打卡
        else if (attends.size() == 1 && attends.get(0).getIsCome() && attends.get(0).getPunchTime() == null) {
            return COME_PUNCH;
        } else if (attends.size() == 1 && attends.get(0).getPunchTime() == null)
            return LEAVE_PUNCH;
        else if (attends.size() == 2) {
            // 可以上班,下班打卡
            if (attends.get(0).getPunchTime() == null && attends.get(1).getPunchTime() == null)
                return BOTH_PUNCH;
            else if (attends.get(1).getPunchTime() == null)
                return LEAVE_PUNCH; // 只可以下班打卡
            else
                return NO_PUNCH;    // 不能打卡
        }
        return NO_PUNCH;
    }

    // 打卡
    @Override
    public int punch(String user, String dutyDay, boolean isCome) {
        Employee emp = empDao.findByName(user);
        if (emp == null)
            return PUNCH_FAIL;

        // 找到员工本次打卡对应的出勤记录
        Attend attend = attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
        if (attend == null)
            return PUNCH_FAIL;

        // 已经打卡
        if (attend.getPunchTime() != null)
            return PUNCHED;
        System.out.println("=============打卡==========");
        // 获取打卡时间
        int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        attend.setPunchTime(new Date());
        // 上班打卡
        if (isCome) {
            // 9点之前算正常
            if (punchHour < COME_LIMIT)
                attend.setType(typeDao.get(AttendType.class, 1));
                // 9-11点之间算迟到
            else if (punchHour < LATE_LIMIT)
                attend.setType(typeDao.get(AttendType.class, 4));
            // 11点之后算旷工,无须理会
        } else //下班打卡
        {
            // 18点之后算正常
            if (punchHour >= LEAVE_LIMIT)
                attend.setType(typeDao.get(AttendType.class, 1));
            else // 16-18点之间算早退
                attend.setType(typeDao.get(AttendType.class, 5));
        }
        attendDao.update(attend);
        return PUNCH_SUCC;
    }


    // 根据员工浏览自己的工资
    @Override
    public List<PaymentBean> empSalary(String empName) {
        // 获取当前员工
        Employee emp = empDao.findByName(empName);

        // 获取该员工的全部工资列表
        List<Payment> pays = payDao.findByEmp(emp);
        List<PaymentBean> result = new ArrayList<PaymentBean>();
        // 分装VO集合
        for (Payment p : pays) {
            result.add(new PaymentBean(p.getPayMonth(), p.getAmount()));
        }
        return result;
    }

    // 员工查看自己的最近三天非正常打卡
    @Override
    public List<AttendBean> unAttend(String empName) {
        // 找出正常上班
        AttendType type = typeDao.get(AttendType.class,1);
        Employee emp = empDao.findByName(empName);
        // 找出非正常上班的出勤记录
        List<Attend> attends = attendDao.findByEmpUnAttend(emp,type);
        List<AttendBean> result = new ArrayList<AttendBean>();
        // 封装VO集合
        for(Attend att: attends){
            result.add(new AttendBean(att.getId(), att.getDutyDay(), att.getType().getName(), att.getPunchTime()));
        }

        return result;
    }

    // 返回全部的出勤类别
    @Override
    public List<AttendType> getAllType() {
        return typeDao.findAll(AttendType.class);
    }

    // 添加申请,用于申诉
    @Override
    public boolean addApplication(int attId, int typeId, String reason) {
        System.out.println("-------------"+attId);
        System.out.println("----"+typeId);
        System.out.println("----"+reason);
        // 创建一个申请
        Application app = new Application();
        // 获取申请需要改变的出勤记录
        Attend attend = attendDao.get(Attend.class, attId);
        AttendType type = typeDao.get(AttendType.class, typeId);
        app.setAttend(attend);
        app.setType(type);
        if(reason!=null)
            app.setReason(reason);
        System.out.println("====aaaaaaaa====");
        appDao.save(app);
        return true;
    }
}

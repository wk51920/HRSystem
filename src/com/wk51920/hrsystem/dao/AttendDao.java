package com.wk51920.hrsystem.dao;

import com.wk51920.common.dao.BaseDao;
import com.wk51920.hrsystem.domain.Attend;
import com.wk51920.hrsystem.domain.AttendType;
import com.wk51920.hrsystem.domain.Employee;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
// 桥接模式 出勤记录
public interface AttendDao extends BaseDao<Attend> {
    // 根据员工 月份查询该员工的出勤记录 (某员工某月的所有出勤情况)
    List<Attend> findByEmpAndMonth(Employee emp, String month);

    // 根据员工,日期查询该员工的打卡记录,某员工某天的所有出勤情况
    List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);

    // 根据员工,日期,上下班查询员工的打卡记录集合,某员工某天上班或下班的具体出勤情况
    Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome);

    // 查看员工前三天的非正常打卡
    List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}

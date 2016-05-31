package com.wk51920.hrsystem.dao.impl;

import com.wk51920.common.dao.impl.BaseDaoHibernate4;
import com.wk51920.hrsystem.dao.AttendDao;
import com.wk51920.hrsystem.domain.Attend;
import com.wk51920.hrsystem.domain.AttendType;
import com.wk51920.hrsystem.domain.Employee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class AttendDaoHibernate4 extends BaseDaoHibernate4<Attend> implements AttendDao {
    @Override
    public List<Attend> findByEmpAndMonth(Employee emp, String month) {
        return find("from Attend as a where a.employee=?0 "+
        "and substring(a.dutyDay,0,7)=?1",emp,month);
    }

    @Override
    public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay) {
        return find("from Attend as a where a.employee=?0 "+
        "and a.dutyDay=?1",emp,dutyDay);
    }

    @Override
    public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome) {
        // 先缩小范围
        List<Attend> al = findByEmpAndDutyDay(emp,dutyDay);
        if(al!=null || al.size()>1){
            for(Attend attend:al){
                if(attend.getIsCome()==isCome)
                    return attend;
            }
        }
        return null;
    }

    // 查询员工前三天异常出勤记录
    @Override
    public List<Attend> findByEmpUnAttend(Employee emp, AttendType type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String end = sdf.format(c.getTime()); // 当天日期
        c.add(Calendar.DAY_OF_MONTH,-3);      // 三天前日期
        String start = sdf.format(c.getTime());
        return find("from Attend as a where a.employee=?0 and "
        + "a.type != ?1 and a.dutyDay between ?2 and ?3", emp,type,start,end);
    }
}

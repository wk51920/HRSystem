package com.wk51920.hrsystem.schedule;

import com.wk51920.hrsystem.service.EmpManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by wk51920 on 16/5/31.
 */
public class PunchJob extends QuartzJobBean {
    // 判断作业是否执行的旗标
    private boolean isRunning = false;
    // 该作业所依赖的业务逻辑组件
    private EmpManager empMgr;

    public void setEmpMgr(EmpManager empMgr) {
        this.empMgr = empMgr;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (!isRunning) {
            System.out.println("开始调查自动打卡");
            isRunning = true;
            empMgr.autoPunch();
            isRunning = false;
        }
    }
}

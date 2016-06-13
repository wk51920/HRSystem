package com.wk51920.hrsystem.service.impl;

import com.wk51920.hrsystem.dao.ApplicationDao;
import com.wk51920.hrsystem.dao.AttendDao;
import com.wk51920.hrsystem.dao.EmployeeDao;
import com.wk51920.hrsystem.domain.Attend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wk51920 on 16/6/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml","file:web/WEB-INF/daoContext.xml"}) //加载配置文件
public class EmpManagerImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    private String exceptName;
    private String paramName;

    private EmployeeDao empDao;
    private ApplicationDao appDao;
    private AttendDao attendDao;


    @Resource(name = "attendDao")
    public void setAttendDao(AttendDao attendDao) {
        this.attendDao = attendDao;
    }

    @Resource(name = "employeeDao")
    public void setEmpDao(EmployeeDao empDao) {
        this.empDao = empDao;
    }

    @Resource(name = "appDao")
    public void setAppDao(ApplicationDao appDao) {
        this.appDao = appDao;
    }

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void autoPunch() throws Exception {

    }

}
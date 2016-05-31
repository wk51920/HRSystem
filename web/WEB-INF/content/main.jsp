<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 下午3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Java EE简单工作流系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="header.jsp"%>
<table width="960" align="center" background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td colspan="3">请单击后面连接开始使用系统
        <a href="login.action">登录系统</a></td>
    </tr>
    <tr>
        <td colspan="3">
            <br/>
            <p align="center">
                <span class="pt11">
                    这仅仅是一个Java EE框架程序.应用模拟一个简单的工作流系统.系统包括两个角色.<br/>
                    普通员工的功能包括员工出勤打卡,员工的人事异动申请,员工查看工资单.<br/>
                    经理的功能包括管理部门运功,签核申请,每月工资自动结算等.
                </span>
            </p>
            <p align="center" class="pt11">
                应用模拟了简单的工作流,使用的轻量级Java EE架构.技术包括:Struts 2.3, Spring 4.0,
                Hibernate 4.3, Quartz 2.2. 整个系统使用Spring提供的DAO支持操作数据库,同时利用Spring
                的声明式事务.<br/>
                程序中的权限检查使用Spring的AOP框架支持,也利用了Spring的任务调度抽象<br/>
                Hibernate为底层的数据库访问提供支持,作为ORM框架使用.
            </p>
        </td>
    </tr>
</table>
<%@include file="footer.jsp"%>
</body>
</html>

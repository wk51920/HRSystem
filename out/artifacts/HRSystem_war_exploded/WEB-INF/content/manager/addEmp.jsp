<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 下午3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>增加新员工</title>
    <s:head/>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            请您输入新员工的资料：<br>
            <s:if test="actionMessages.size()>0">
            <div class="error">
                <s:actionmessage/>
            </div>
            </s:if>
            <div align="center">
                <s:form action="processAdd">
                    <s:textfield name="emp.name" label="员工用户名"/>
                    <s:textfield name="emp.pass" label="员工密码"/>
                    <s:textfield name="emp.salary" label="员工月薪"/>
                    <s:token/>
    <tr><td colspan="2">
        <s:submit value="添加新员工" theme="simple"/>
        <s:reset  theme="simple" value="重新输入"/>
    </td></tr>
    </s:form>
    </div>
    </td>
    </tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 下午3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>查看本部门上个月的发薪</title>
    <link href="images/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            <table width=80% border=0 align="center"
                   cellspacing="1" bgcolor="#cccccc">
                <tr bgcolor="#e1e1e1" >
                    <td colspan="5" ><div class="mytitle">您正在查看上个月部门的全部工资</div></td>
                </tr>
                <tr class="pt11" height="45">
                    <td><b>员工名</b></td>
                    <td><b>薪水</b></td>
                </tr>
                <s:iterator value="sals" status="index">
                    <s:if test="#index.odd == true">
                        <tr style="background-color:#dddddd" class="pt11" height="32">
                    </s:if>
                    <s:else>
                        <tr class="pt11" height="32">
                    </s:else>
                    <td><s:property value="empName"/></td>
                    <td><s:property value="amount"/></td>
                    </tr>
                </s:iterator>
            </table>
        </td>
    </tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>

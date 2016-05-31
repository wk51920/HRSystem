<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 下午3:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>ｵ釋ﾓｴｨ</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td colspan="3"><br/><br/><div class="mytitle">打卡</div></td>
    </tr>
    <tr>
        <td colspan="3" style="text-align : center;">
            <br/>

            <s:if test="punchIsValid==1
	|| punchIsValid==3">
                <s:form action="employeeCome" theme="simple">
                    <s:submit value="上班打卡"/>
                </s:form>
            </s:if>
            <s:if test="punchIsValid==2
	|| punchIsValid==3">
                <s:form action="employeeLeave" theme="simple">
                    <s:submit value="下班打卡"/>
                </s:form>
            </s:if>
            <br/>
        </td>
    </tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>
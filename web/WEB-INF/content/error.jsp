<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 上午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>出错提示页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<%@include file="header.jsp"%>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td height="39"><br>
            <div align="center"><font color="#FF0000" size="+1"><b>系统处理过程中发生了一个错误，信息如下：</b></font></div>
        </td>
    </tr>
    <tr>
        <td height="315">
            <div class="pt11" style="color:red;width:600px;height:300px;
	border:1px solid black;border-radius:10px">
                <s:property value="exception.message"/>
        </td>
    </tr>
    <tr>
        <td><div align="center" style="font:large;color:#333333">
            请您先核对输入，如果再次出现该错误，请登录<a href="http//github.com/wk51920">http//github.com/wk51920</a>寻求答案，谢谢。
        </div><br></td>
    </tr>
</table>
<%@include file="footer.jsp"%>
</body>
</html>
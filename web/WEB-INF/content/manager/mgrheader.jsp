<%--
  Created by IntelliJ IDEA.
  User: wk51920
  Date: 16/5/31
  Time: 下午3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<table width="960"  border="0" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td width="83"><div align="center"><a href="managerPunch.action">打卡</a></div></td>
        <td width="104"><div align="center"><a href="viewmanagerSalary.action">查看历史工资</a></div></td>
        <td width="94"><div align="center"><a href="viewApp.action">签核申请</a></div></td>
        <td width="139"><div align="center"><a href="viewEmp.action">管理部门员工</a></div></td>
        <td width="94"><div align="center"><a href="addEmp.action">新增员工</a></div></td>
        <td width="160"><div align="center"><a href="viewDeptSal.action">查看上月部门发薪</a></div></td>
        <td width="76"><div align="center"><a href="logout.action">退出系统</a></div></td>
    </tr>
</table>

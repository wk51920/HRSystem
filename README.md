    应用模拟一个简单的工作流系统.系统包括两个角色.
    普通员工的功能包括员工出勤打卡,员工的人事异动申请,员工查看工资单.
    经理的功能包括管理部门运功,签核申请,每月工资自动结算等.
    应用模拟了简单的工作流,使用的轻量级Java EE架构.技术包括:Struts 2.3, Spring 4.0,Hibernate 4.3, Quartz 2.2. 整个系统使用Spring提供的DAO支持操作数据库,同时利用Spring的声明式事务.
    程序中的权限检查使用Spring的AOP框架支持,也利用了Spring的任务调度抽象Hibernate为底层的数据库访问提供支持,作为ORM框架使用.
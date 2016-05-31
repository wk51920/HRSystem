package com.wk51920.hrsystem.dao;

import com.wk51920.common.dao.BaseDao;
import com.wk51920.hrsystem.domain.Manager;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public interface ManagerDao extends BaseDao<Manager> {
    // 根据用户名和密码查询经理
    List<Manager> findByNameAndPass(Manager mgr);

    // 根据用户名查找经理
    Manager findByName(String name);

}

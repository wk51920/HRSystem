package com.wk51920.hrsystem.dao.impl;

import com.wk51920.common.dao.impl.BaseDaoHibernate4;
import com.wk51920.hrsystem.dao.ManagerDao;
import com.wk51920.hrsystem.domain.Manager;

import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class ManagerDaoHibernate4 extends BaseDaoHibernate4<Manager> implements ManagerDao {
    @Override
    public List<Manager> findByNameAndPass(Manager mgr) {
        return find("select m from Manager m where m.name = ?0 and m.pass = ?1"
                , mgr.getName(), mgr.getPass());
    }

    @Override
    public Manager findByName(String name) {
        List<Manager> ml = find("select m from Manager m where m.name=?0", name);
        if (ml != null && ml.size() >= 1)
            return ml.get(0);
        return null;
    }
}

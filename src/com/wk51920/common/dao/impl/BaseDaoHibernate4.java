package com.wk51920.common.dao.impl;

import com.wk51920.common.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wk51920 on 16/5/30.
 */
public class BaseDaoHibernate4<T> implements BaseDao<T> {
    // 以来Spring注入Session Factory
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // 根据ID得到实体
    @SuppressWarnings("unchecked")
    @Override
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
    }

    // 保存实体
    @Override
    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    // 更新实体
    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    // 删除实体
    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    // 根据ID删除实体
    @Override
    public void delete(Class<T> entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession().createQuery("delete " +
                entityClazz.getSimpleName() + " en where en.id=?0").setParameter("0", id).executeUpdate();
    }

    // 获取所有实体
    @Override
    public List<T> findAll(Class<T> entityClazz) {
        return find("from "+entityClazz.getSimpleName()+" en");
    }

    // 获取实体总数
    @Override
    public long findCount(Class<T> entityClazz) {
        List<?> l = find("select count(*) from "+entityClazz.getSimpleName());
        // 返回查询得到的实体总数
        if(l!=null && l.size()==1){
            return (Long)l.get(0);
        }
        return 0;
    }

    // 根据HQL语句查询实体
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        return (List<T>) getSessionFactory().getCurrentSession()
                .createQuery(hql).list();
    }

    // 根据带占位符参数HQL语句查询实体
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... params) {
        // 创建查询
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        // 为每个占位符的HQL语句设置参数
        for (int i = 0, len = params.length; i < len; i++) {
            query.setParameter(i + "", params[i]);
        }
        return (List<T>) query.list();
    }

    // 使用hql语句进行分页查询,不带占位符
    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql, int pageNo, int pageSize) {
        // 创建查询
        return getSessionFactory().getCurrentSession().createQuery(hql)
                // 执行分页
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    // 分页查询带占位符
    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql, int pageNo, int pageSize
    , Object... params){
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        for(int i = 0, len=params.length; i<len ;i++){
            query.setParameter(i+"",params[i]);
        }
        return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
    }
}

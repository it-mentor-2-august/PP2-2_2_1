package hiber.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
public abstract class HibernateDao<T, E extends Serializable> {
    private Class<T> clazz;

    private SessionFactory sessionFactory;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T getById(final E id) {
        return getCurrentSession().get(clazz, id);
    }
    @SuppressWarnings("unchecked")
    public List<T> findAll(){
        return getCurrentSession().createQuery(String.format("from %s", clazz.getName())).getResultList();
    }

    public void add(final T entity){
        getCurrentSession().persist(entity);
    }

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

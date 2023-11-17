package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImp extends HibernateDao<User, Long> implements UserDao {

   public UserDaoImp() {
      super();
      this.setClazz(User.class);
   }

   public List<User> getUserByCarModelAndSeries(String model, int series) {
//      String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
      String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
      Query<User> userQuery = this.getCurrentSession().createQuery(hql, this.getClazz());
      userQuery.setParameter("model", model);
      userQuery.setParameter("series", series);
      return userQuery.getResultList();
   }


}

package hiber.service;

import hiber.dao.AbstractDaoInterface;
import hiber.dao.HibernateDao;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.findAll();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUserByCarModelandSeries(String model, int series) {
      return userDao.getUserByCarModelAndSeries(model, series);
   }


}

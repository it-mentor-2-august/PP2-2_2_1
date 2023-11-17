package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao extends AbstractDaoInterface<User, Long> {
   List<User> getUserByCarModelAndSeries(String m, int s);
}

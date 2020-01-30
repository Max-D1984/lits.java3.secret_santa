package service;


import dal.UserDal;
import dal.UserDalImpl;
import pojo.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {


      @Override
    public void updateUser(User user, User newUser) {
          UserDal userDal = new UserDalImpl();
          userDal.update(user, newUser);
    }

    @Override
    public void deleteUser(User user) {
          UserDal userDal=new UserDalImpl();
          userDal.delete(user);
    }

    @Override
    public void createUser(User user) {
          UserDal userDal=new UserDalImpl();
          userDal.create(user);

    }

    @Override
    public User readUser(long id) {
        UserDal userDal = new UserDalImpl();
        return userDal.read(id);
    }


    @Override
    public List<User> readUserList() {

        UserDal userDal = new UserDalImpl();
        return userDal.readList();
    }



    @Override
    public void testUser() {
        readUserList().stream().forEach(y -> System.out.println(y.getUserName()));

    }

}

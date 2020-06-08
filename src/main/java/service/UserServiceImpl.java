package service;


import dal.UserDal;
import dal.UserDalImpl;
import pojo.User;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {

    UserDal userDal = new UserDalImpl();

    @Override
    public void updateUser(User user, User newUser) {
        userDal.update(user, newUser);
    }

    @Override
    public void deleteUser(User user) {
        userDal.delete(user);
    }

    @Override
    public void createUser(User user) {
        userDal.create(user);
    }

    @Override
    public User readUser(long id) {
        return userDal.read(id);
    }

    @Override
    public User readUserByEmail(String email) { return userDal.readUserByEmail(email);    }

    @Override
    public List<User> readUserList() {
        return userDal.readList();
    }

    @Override
    public void testUser() {
        readUserList().stream().forEach(y -> System.out.println(y.getUserName()));
    }

    @Override
    public List getAllTargetsForUser(List<Integer> listOfCompanys, List<Integer> listOfTargets) {
        return userDal.getAllTargetsForUser(listOfCompanys, listOfTargets);
    }


}

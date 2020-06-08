package service;

import pojo.User;

import java.util.List;

public interface UserService {
    void updateUser(User user, User newUser);
    void deleteUser(User user);

    void createUser(User user);

    User readUser(long id);
    User readUserByEmail(String email);


    List<User> readUserList();
    void testUser();
    List getAllTargetsForUser(List<Integer> listOfCompanys, List<Integer> listOfTargets);

}


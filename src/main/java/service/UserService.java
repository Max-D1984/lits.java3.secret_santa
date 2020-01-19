package service;

import pojo.User;

public interface UserService {
    void updateUser();
    void deleteUser();

    void createRule(User user);

    void readUser();

    void readUserList();
    void testUser();

}


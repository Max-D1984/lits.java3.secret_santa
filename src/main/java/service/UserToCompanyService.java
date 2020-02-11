package service;

import pojo.UserToCompany;

import java.util.List;

public interface UserToCompanyService {
    UserToCompany readUserToCompany(long id);

    List<UserToCompany> readList();

    void createUserToCompany(UserToCompany userToCompany);

    void deleteUserToCompany(UserToCompany userToCompany);

    void updateUserToCompany(UserToCompany userToCompany, int newUser_id, int newCompany_id);

    void testUserToCompany();
}
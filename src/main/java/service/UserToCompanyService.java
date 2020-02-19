package service;

import model.UserAndUserTargetId;
import pojo.User;
import pojo.UserToCompany;

import java.util.List;

public interface UserToCompanyService {
    UserToCompany readUserToCompany(long id);

    List<UserToCompany> readList();

    void createUserToCompany(UserToCompany userToCompany);

    void deleteUserToCompany(UserToCompany userToCompany);

    void updateUserToCompany(UserToCompany userToCompany, int newUser_id, int newCompany_id, String newRole);

    void testUserToCompany();

    List<User> readUserByCompanyId(int id);

    List<UserToCompany> readListByCompanyId(long id);

    List<Integer> getCompanysByUserId(int user_id);
    List<Integer>getUsersOfCompany(int company_id);

}

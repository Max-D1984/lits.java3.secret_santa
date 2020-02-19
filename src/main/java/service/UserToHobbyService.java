package service;

import pojo.UserToHobby;

import java.util.List;

public interface UserToHobbyService {
    UserToHobby readUserToHobby(long id);

    List<UserToHobby> readList();

    List<UserToHobby> readListByUserId(int userId);

    void createUserToHobby(UserToHobby userToHobby);

    void deleteUserToHobby(UserToHobby userToHobby);

    void updateUserToHobby(UserToHobby userToHobby, int newUser_id, int newHobby_id);

    void testUserToHobby();
}

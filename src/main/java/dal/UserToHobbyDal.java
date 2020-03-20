package dal;

import pojo.UserToHobby;

import java.util.List;

public interface UserToHobbyDal {
    UserToHobby read (long id);
    List<UserToHobby> readList ();
    List<UserToHobby> readListByUserId (long userId);
    void createUserToHobby (UserToHobby userToHobby);
    void update (UserToHobby userToHobby, int newUser_id, int newHobby_id);
    void delete (UserToHobby userToHobby);
}

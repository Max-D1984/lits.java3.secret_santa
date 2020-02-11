package dal;

import pojo.UserToHobby;

import java.util.List;

public interface UserToHobbyDal {
    public UserToHobby read (long id);
    public List<UserToHobby> readList ();
    public void createUserToHobby (UserToHobby userToHobby);
    public void update (UserToHobby userToHobby, int newUser_id, int newHobby_id);
    public void delete (UserToHobby userToHobby);
}

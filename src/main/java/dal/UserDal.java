package dal;

import pojo.User;

import java.util.List;

public interface UserDal {

    public void create (User user);
    public User read(long id);
    public void update (User user, User newUser);
    public void delete (User user);
    public List<User> readList ();

}

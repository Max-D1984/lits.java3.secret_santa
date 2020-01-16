package dal;

import pojo.User;

import java.util.List;

public interface UserDal {

    public void create (User user);
    public User read (long id);
    public void update (long id, User user);
    public void delete (long id);
    public List<User> readList ();

}

package dal;


import model.MyWishListResponse;
import pojo.UserToPresent;

import java.util.List;

public interface UserToPresentDal {
    void create (UserToPresent userToPresent);
    UserToPresent read (int id);
    List<UserToPresent> readByUser(int id);
    void update (int id, UserToPresent userToPresent);
    void delete (int id);
    List<UserToPresent> readList ();
    List<MyWishListResponse> readPresentListById(int id);
}

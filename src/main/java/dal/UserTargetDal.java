package dal;

import pojo.User;
import pojo.UserTarget;

import java.util.List;
import java.util.Map;

public interface UserTargetDal {
    void create (UserTarget userTarget);
    UserTarget read (int id);
    void update (int id, UserTarget userTarget);
    void delete (int id);
    List<UserTarget> readList ();
    void createList(Map<Integer,Integer> mapOfSecretSanta);
}

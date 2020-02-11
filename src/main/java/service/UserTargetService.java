package service;

import pojo.User;
import pojo.UserTarget;
import java.util.List;
import java.util.Map;

public interface UserTargetService {
    void update (int id, UserTarget userTarget);
    void delete (int id);
    void create (UserTarget userTarget);
    UserTarget read (int id);
    List<UserTarget> readList ();
    void createList(List<User> userList);
    Map<Integer,Integer> generateMapOfUsers(List<User> userList);
}
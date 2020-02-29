package service;

import model.UserAndUserTargetId;
import model.UserResponse;
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
    void createList(List<UserResponse> userList);
    Map<Integer,Integer> generateMapOfUsers(List<UserResponse> userList);
    List getTargetForUserById (int id);
    List<UserAndUserTargetId> getTargetForUserInCompany (int user_id, List<Integer> target_id);
}

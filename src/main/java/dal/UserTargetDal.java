package dal;

import model.UserAndUserTargetId;
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
    List getTargetForUserById (int id);
    List<Integer> getTargetsIdByUsersId (List<Integer> usersIds);
    List<UserAndUserTargetId> getTargetForUserInCompany (int user_id, List<Integer> target_id);
    List<Integer> getSantasInCompany(Integer companyId);
}

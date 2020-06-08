package dal;

import pojo.User;

import java.util.List;

public interface UserDal {

     void create (User user);
     User read(long id);
     User readUserByEmail(String email);
     void update (User user, User newUser);
     void delete (User user);
     List<User> readList ();
     List<String> getUsersNamesbyIdCompany (List<Integer> listOfId);
     List getAllTargetsForUser(List<Integer> listOfCompanys, List<Integer> listOfTargets);
}

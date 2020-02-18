package dal;

import pojo.User;
import pojo.UserToCompany;

import java.util.List;

public interface UserToCompanyDal {
     UserToCompany read(long id);
     List<UserToCompany> readList();
     void createUserToCompany(UserToCompany userToCompany);
     void update(UserToCompany userToCompany, int newUser_id, int newCompany_id,String newRole);
     void delete(UserToCompany userToHobby);
     List<User>readListByCompanyId(int id);
     List<UserToCompany>readListByCompanyId(long id);
     List<Integer>getUsersOfCompany(int company_id);
     List<Integer> getCompanysByUserId(int user_id);


}

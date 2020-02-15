package dal;

import pojo.User;
import pojo.UserToCompany;

import java.util.List;

public interface UserToCompanyDal {
     UserToCompany read(long id);
     List<UserToCompany> readList();
     void createUserToCompany(UserToCompany userToHobby);
     void update(UserToCompany userToHobby, int newUser_id, int newCompany_id,String newRole);
     void delete(UserToCompany userToHobby);
     List<User>readListByCompanyId(int id);

}

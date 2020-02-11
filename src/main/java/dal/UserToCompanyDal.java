package dal;

import pojo.UserToCompany;

import java.util.List;

public interface UserToCompanyDal {
    public UserToCompany read(long id);
    public List<UserToCompany> readList();
    public void createUserToCompany(UserToCompany userToHobby);
    public void update(UserToCompany userToHobby, int newUser_id, int newCompany_id);
    public void delete(UserToCompany userToHobby);
}

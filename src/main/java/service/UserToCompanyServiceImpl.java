package service;

import dal.UserToCompanyDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import pojo.UserToCompany;

import java.util.List;
@Service
public class UserToCompanyServiceImpl implements UserToCompanyService {

    @Autowired
    private UserToCompanyDal userToCompanyDal ;

    public UserToCompanyDal getUserToCompanyDal() {
        return userToCompanyDal;
    }

    @Override
    public UserToCompany readUserToCompany(long id) {
        return userToCompanyDal.read(id);
    }

    @Override
    public List<UserToCompany> readList() {
        return userToCompanyDal.readList();
    }

    @Override
    public void createUserToCompany(UserToCompany userToCompany) {
        userToCompanyDal.createUserToCompany(userToCompany);
    }

    @Override
    public void deleteUserToCompany(UserToCompany userToCompany) {
        userToCompanyDal.delete(userToCompany);
    }

    @Override
    public void updateUserToCompany(UserToCompany userToCompany, int newUser_id, int newCompany_id, String newRole) {
        userToCompanyDal.update(userToCompany, newUser_id, newCompany_id, newRole);
    }

    @Override
    public void testUserToCompany() {

    }

    @Override
    public List<User> readUserByCompanyId(int id) {
        return userToCompanyDal.readListByCompanyId(id);
    }

    @Override
    public List<UserToCompany> readListByCompanyId(long id) {
        return userToCompanyDal.readListByCompanyId(id);
    }

    @Override
    public List<Integer> getCompanysByUserId(int user_id) {
        return userToCompanyDal.getCompanysByUserId(user_id);
    }

    @Override
    public List<Integer> getUsersOfCompany(int company_id) {return userToCompanyDal.getUsersOfCompany(company_id);
    }


}

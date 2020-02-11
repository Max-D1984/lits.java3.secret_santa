package service;

import dal.UserToCompanyDal;
import dal.UserToCompanyDalImpl;
import pojo.UserToCompany;

import java.util.List;

public class UserToCompanyServiceImpl implements UserToCompanyService{
    private UserToCompanyDal userToCompanyDal = new UserToCompanyDalImpl();

    @Override
    public UserToCompany readUserToCompany(long id) {return userToCompanyDal.read(1);
    }

    @Override
    public List<UserToCompany> readList() {return userToCompanyDal.readList();
    }

    @Override
    public void createUserToCompany(UserToCompany userToCompany) {userToCompanyDal.createUserToCompany(userToCompany);}

    @Override
    public void deleteUserToCompany(UserToCompany userToCompany) {userToCompanyDal.delete(userToCompany);}

    @Override
    public void updateUserToCompany(UserToCompany userToCompany, int newUser_id, int newHobby_id)
    {userToCompanyDal.update(userToCompany,newUser_id,newHobby_id);}

    @Override
    public void testUserToCompany() {

    }
}
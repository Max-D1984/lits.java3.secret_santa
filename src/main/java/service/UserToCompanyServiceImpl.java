package service;

import dal.UserToCompanyDal;
import dal.UserToCompanyDalImpl;
import model.UserResponse;
import pojo.UserToCompany;
import java.util.List;

//@Service
public class UserToCompanyServiceImpl implements UserToCompanyService {

   // @Autowired
    private UserToCompanyDal userToCompanyDal = new UserToCompanyDalImpl();

//    public UserToCompanyDal getUserToCompanyDal() {
//        return userToCompanyDal;
//    }

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
    public List<UserResponse> readUserByCompanyId(int id) {
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

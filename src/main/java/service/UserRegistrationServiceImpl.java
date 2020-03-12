package service;

import dal.UserDal;
import dal.UserDalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRegistrationServiceImpl implements UserRegistrationService{
    UserDal userDal = new UserDalImpl();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(User user) {

        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        userDal.create(user);
    }
}

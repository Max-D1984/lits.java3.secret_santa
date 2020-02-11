package service;

import dal.UserToHobbyDal;
import dal.UserToHobbyDalImpl;
import pojo.UserToHobby;

import java.util.List;

public class UserToHobbyServiceImpl implements UserToHobbyService{
    private UserToHobbyDal userToHobbyDal = new UserToHobbyDalImpl();

    @Override
    public UserToHobby readUserToHobby(long id) {return userToHobbyDal.read(1);
    }

    @Override
    public List<UserToHobby> readList() {return userToHobbyDal.readList();
    }

    @Override
    public void createUserToHobby(UserToHobby userToHobby) {userToHobbyDal.createUserToHobby(userToHobby);}

    @Override
    public void deleteUserToHobby(UserToHobby userToHobby) {userToHobbyDal.delete(userToHobby);}

    @Override
    public void updateUserToHobby(UserToHobby userToHobby, int newUser_id, int newHobby_id)
    {userToHobbyDal.update(userToHobby,newUser_id,newHobby_id);}

    @Override
    public void testUserToHobby() {

    }
}

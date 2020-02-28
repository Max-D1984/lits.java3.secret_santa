package service;

import dal.UserToPresentDal;
import dal.UserToPresentDalImpl;
import model.MyWishListResponse;
import org.springframework.stereotype.Service;
import pojo.UserToPresent;

import java.util.List;

@Service
public class UserToPresentServiceImpl implements UserToPresentService {
    private UserToPresentDal userToPresentDal = new UserToPresentDalImpl();
    @Override
    public void create(UserToPresent userToPresent) {
        userToPresentDal.create(userToPresent);
    }

    @Override
    public UserToPresent read(int id) {
        return userToPresentDal.read(id);
    }

    @Override
    public List<UserToPresent> readByUser(int id) {
        return userToPresentDal.readByUser(id);
    }

    @Override
    public void update(int id, UserToPresent userToPresent) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<UserToPresent> readList() {
        return userToPresentDal.readList();
    }

    @Override
    public List<MyWishListResponse> readPresentListById(int id) {
        return userToPresentDal.readPresentListById(id);
    }
}

package service;

import dal.HobbyDal;
import dal.HobbyDalImpl;
import pojo.Hobby;
import pojo.Present;

import java.util.List;

public class HobbyServiceImpl implements HobbyService {

    private HobbyDal hobbyDal = new HobbyDalImpl();

    @Override
    public void createHobby(Hobby hobby) { hobbyDal.createHobby(hobby); }

    @Override
    public Hobby readHobby(long id) {return hobbyDal.read(id);  }

    @Override
    public List<Hobby> readList() {return hobbyDal.readList();  }

    @Override
    public void deleteHobby(Hobby hobby) {hobbyDal.delete (hobby); }

    @Override
    public void updateHobby(Hobby hobby, String newName) {hobbyDal.update(hobby, newName);
    }

    @Override
    public void testHobby() {
    }


}

package service;

import dal.HobbyDal;
import dal.HobbyDalImpl;
import org.springframework.stereotype.Service;
import pojo.Hobby;
import pojo.Present;

import java.util.List;

@Service
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
    @Override
    public List<Hobby> readListByHobbysId(List<Integer> id){
        return hobbyDal.readListByHobbysId(id);
    }


}

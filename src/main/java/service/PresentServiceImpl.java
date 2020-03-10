package service;


import dal.PresentDal;
import dal.PresentDalImpl;
import org.springframework.stereotype.Service;
import pojo.Present;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service
public class PresentServiceImpl implements PresentService {

    private PresentDal presentDal = new PresentDalImpl();

    @Override
    public void createPresent(Present present) { presentDal.createPresent(present); }

    @Override
    public Present readPresent(long id) {return presentDal.read(id);  }

    @Override
    public List<Present> readList() {return presentDal.readList();  }

    @Override
    public void deletePresent(Present present) {presentDal.delete (present); }

    @Override
    public void updatePresent(Present present, String newName, String newUrl) {presentDal.update(present, newName, newUrl);
    }

    @Override
    public void testPresent() {
       }
    public List<Present> readListByPresentsId(List<Integer> id){
        return presentDal.readListByPresentsId(id);
    }

    public Present readIdByNameAndURL(String presentName, String presentUrl){
       return  presentDal.readIdByNameAndURL(presentName,presentName);
    }


}



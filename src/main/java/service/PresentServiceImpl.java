package service;


import dal.PresentDal;
import dal.PresentDalImpl;
import pojo.Present;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

}



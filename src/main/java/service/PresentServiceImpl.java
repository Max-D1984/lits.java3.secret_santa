package service;


import dal.PresentDal;
import dal.PresentDalImpl;
import pojo.Present;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PresentServiceImpl implements PresentService {

    @Override
    public void createPresent(Present present) {
        PresentDal presentDal = new PresentDalImpl();
        presentDal.createPresent(present);
    }

    @Override
    public Present readPresent(long id) {
        PresentDal presentDal = new PresentDalImpl();
        return presentDal.read(id);


    }

    @Override
    public List<Present> readList() {
        PresentDal presentDal = new PresentDalImpl();
        return presentDal.readList();
    }

    @Override
    public void deletePresent(Present present) {
        PresentDal presentDal = new PresentDalImpl();
        presentDal.delete (present);

    }

    @Override
    public void updatePresent(Present present, String newName, String newUrl) {
        PresentDal presentDal = new PresentDalImpl();
        presentDal.update(present, newName, newUrl);
    }

    @Override
    public void testPresent() {
       }

}



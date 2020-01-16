package dal;

import pojo.Present;

import java.util.List;

public interface PresentDal {
    public void create (Present present);
    public Present read (long id);
    public void update (long id, Present pres);
    public void delete (long id);
    public List<Present> readList ();



}

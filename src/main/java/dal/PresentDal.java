package dal;

import pojo.Present;

import java.util.List;

public interface PresentDal {
    public Present read (long id);
    public List<Present> readList ();
    public void createPresent (Present present);
    public void update (Present present, String newName, String newUrl);
    public void delete (Present present);



}

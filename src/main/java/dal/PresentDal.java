package dal;

import org.slf4j.Logger;
import pojo.Present;

import java.util.List;

public interface PresentDal {
    public Present read (long id);
    public List<Present> readList ();
    public void createPresent (Present present);
    public void update (Present present, String newName, String newUrl);
    public void delete (Present present);
    public List<Present> readListByPresentsId(List<Integer> id);
    public Present readIdByNameAndURL(String presentName, String presentUrl);
}

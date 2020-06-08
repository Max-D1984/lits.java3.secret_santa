package dal;

import pojo.Present;

import java.util.List;

public interface PresentDal {
     Present read (long id);
     List<Present> readList ();
     void createPresent (Present present);
     void update (Present present, String newName, String newUrl);
     void delete (Present present);
     List<Present> readListByPresentsId(List<Integer> id);
     Present readIdByNameAndURL(String presentName, String presentUrl);

}

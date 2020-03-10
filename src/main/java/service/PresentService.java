package service;

import pojo.Present;

import java.util.List;

public interface PresentService {

    Present readPresent(long id);

    List<Present> readList();

    void createPresent(Present present);

    void deletePresent(Present present);

    void updatePresent(Present present, String newName, String newUrl);

    void testPresent();
    List<Present> readListByPresentsId(List<Integer> id);
    Present readIdByNameAndURL(String presentName, String presentUrl);

}


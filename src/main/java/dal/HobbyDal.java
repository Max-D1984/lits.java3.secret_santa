package dal;

import pojo.Hobby;

import java.util.List;

public interface HobbyDal {
    Hobby read(long id);
    List<Hobby> readList();
    void createHobby(Hobby hobby);
    void update(Hobby hobby, String newName);
    void delete(Hobby hobby);
    List<Hobby> readListByHobbysId(List<Integer> id);
}

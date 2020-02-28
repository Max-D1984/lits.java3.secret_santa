package service;

import pojo.Hobby;

import java.util.List;

public interface HobbyService {
    Hobby readHobby(long id);

    List<Hobby> readList();

    void createHobby(Hobby hobby);

    void deleteHobby(Hobby hobby);

    void updateHobby(Hobby hobby, String newName);

    void testHobby();

    public List<Hobby> readListByHobbysId(List<Integer> id);
}

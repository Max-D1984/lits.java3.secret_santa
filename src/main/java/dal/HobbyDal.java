package dal;

import pojo.Hobby;

import java.util.List;

public interface HobbyDal {
    public Hobby read(long id);
    public List<Hobby> readList();
    public void createHobby(Hobby hobby);
    public void update(Hobby hobby, String newName);
    public void delete(Hobby hobby);
}

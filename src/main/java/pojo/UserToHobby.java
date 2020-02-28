package pojo;

import java.util.Objects;

public class UserToHobby {
    private int id;
    private int user_id;
    private int hobby_id;
    private int user_santa_id;

    public UserToHobby(int id, int user_id, int hobby_id, int user_santa_id) {
        this.id = id;
        this.user_id = user_id;
        this.hobby_id = hobby_id;
this.user_santa_id =  user_santa_id;
    }

    public UserToHobby(int id, int user_id, int hobby_id) {
        this.id = id;
        this.user_id = user_id;
        this.hobby_id = hobby_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHobby_id() {
        return hobby_id;
    }

    public void setHobby_id(int hobby_id) {
        this.hobby_id = hobby_id;
    }

    public int getUser_santa_id() {
        return user_santa_id;
    }

    public void setUser_santa_id(int user_santa_id) {
        this.user_santa_id = user_santa_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToHobby that = (UserToHobby) o;
        return id == that.id &&
                user_id == that.user_id &&
                hobby_id == that.hobby_id &&
                user_santa_id == that.user_santa_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, hobby_id, user_santa_id);
    }

    @Override
    public String toString() {
        return "UserToHobby{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", hobby_id=" + hobby_id +
                ", user_santa_id=" + user_santa_id +
                '}';
    }
}

package pojo;

import java.util.Objects;

public class Hobby {

    private int id;
    private String name;
    private Integer user_santa_id;


    public Hobby(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return id == hobby.id &&
                user_santa_id == hobby.user_santa_id &&
                Objects.equals(name, hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user_santa_id);
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_santa_id=" + user_santa_id +
                '}';
    }

    public int getUser_santa_id() {
        return user_santa_id;
    }

    public void setUser_santa_id(int user_santa_id) {
        this.user_santa_id = user_santa_id;
    }



    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


}

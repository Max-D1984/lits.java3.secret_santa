package model;

import java.util.Objects;

public class Hobby {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hobby(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(id, hobby.id) &&
                Objects.equals(name, hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "HobbyList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


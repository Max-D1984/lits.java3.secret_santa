package model;

import java.util.Objects;

public class TargetUserHobbyResponse {
    private String name;
    private Integer user_santa_id;

    public TargetUserHobbyResponse(String name, Integer user_santa_id) {
        this.name = name;
        this.user_santa_id = user_santa_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser_santa_id() {
        return user_santa_id;
    }

    public void setUser_santa_id(Integer user_santa_id) {
        this.user_santa_id = user_santa_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetUserHobbyResponse that = (TargetUserHobbyResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(user_santa_id, that.user_santa_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user_santa_id);
    }

    @Override
    public String toString() {
        return "TargetUserHobbyResponse{" +
                "name='" + name + '\'' +
                ", user_santa_id=" + user_santa_id +
                '}';
    }
}

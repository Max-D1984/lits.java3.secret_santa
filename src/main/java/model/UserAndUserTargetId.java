package model;

import java.util.Objects;

public class UserAndUserTargetId {
    Integer user_id;
    Integer target_id;

    public UserAndUserTargetId(Integer user_id, Integer target_id) {
        this.user_id = user_id;
        this.target_id = target_id;
    }

    @Override
    public String toString() {
        return "UserAndUserTargetId{" +
                "user_id=" + user_id +
                ", target_id=" + target_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndUserTargetId that = (UserAndUserTargetId) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(target_id, that.target_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, target_id);
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }
}

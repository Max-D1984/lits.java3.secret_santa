package pojo;

import java.util.Objects;

public class UserToPresent {
    private int id;
    private int userId;
    private int presentId;
    private int user_santa_id;

    public UserToPresent(int id, int userId, int presentId, int user_santa_id) {
        this.id = id;
        this.userId = userId;
        this.presentId = presentId;
        this.user_santa_id = user_santa_id;
    }

    public UserToPresent(int id, int userId, int presentId) {
        this.id = id;
        this.userId = userId;
        this.presentId = presentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPresentId() {
        return presentId;
    }

    public void setPresentId(int presentId) {
        this.presentId = presentId;
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
        UserToPresent that = (UserToPresent) o;
        return id == that.id &&
                userId == that.userId &&
                presentId == that.presentId &&
                user_santa_id == that.user_santa_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, presentId, user_santa_id);
    }

    @Override
    public String toString() {
        return "UserToPresent{" +
                "id=" + id +
                ", userId=" + userId +
                ", presentId=" + presentId +
                ", user_santa_id=" + user_santa_id +
                '}';
    }
}

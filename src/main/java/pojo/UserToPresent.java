package pojo;

import java.util.Objects;

public class UserToPresent {
    private int id;
    private int userId;
    private int presentId;

    public UserToPresent(int id, int userId, int presentId) {
        this.id = id;
        this.userId = userId;
        this.presentId = presentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToPresent that = (UserToPresent) o;
        return id == that.id &&
                userId == that.userId &&
                presentId == that.presentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, presentId);
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

    @Override
    public String toString() {
        return "UserToPresent{" +
                "id=" + id +
                ", userId=" + userId +
                ", presentId=" + presentId +
                '}';
    }
}

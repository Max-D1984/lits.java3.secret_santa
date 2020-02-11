package pojo;

import java.util.Objects;

public class UserTarget {
    private int id;
    private int userId;
    private int userTargetId;
    private String status;

    public UserTarget(int id, int userId, int userTargetId, String status) {
        this.id = id;
        this.userId = userId;
        this.userTargetId = userTargetId;
        this.status = status;
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

    public int getUserTargetId() {
        return userTargetId;
    }

    public void setUserTargetId(int userTargetId) {
        this.userTargetId = userTargetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTarget that = (UserTarget) o;
        return id == that.id &&
                userId == that.userId &&
                userTargetId == that.userTargetId &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userTargetId, status);
    }

    @Override
    public String toString() {
        return "UserTarget{" +
                "id=" + id +
                ", userId=" + userId +
                ", userTargetId=" + userTargetId +
                ", status='" + status + '\'' +
                '}';
    }
}

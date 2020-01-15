package Records;

import java.util.Objects;

public class UserRecords {

    private int id;
    private String userName;
    private String userRole;


    public UserRecords(int id, String userName, String userRole) {
        this.id = id;
        this.userName = userName;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecords that = (UserRecords) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userRole);
    }

    @Override
    public String toString() {
        return "UserRecords{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
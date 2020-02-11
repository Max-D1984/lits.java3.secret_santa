package pojo;

import java.util.Objects;

public class UserToCompany {
    private int id;
    private int user_id;
    private int company_id;

    public UserToCompany(int id, int user_id, int hobby_id) {
        this.id = id;
        this.user_id = user_id;
        this.company_id = hobby_id;

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

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int hobby_id) {
        this.company_id = hobby_id;
    }

    @Override
    public String toString() {
        return "UserToHobby{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", hobby_id=" + company_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToCompany that = (UserToCompany) o;
        return id == that.id &&
                user_id == that.user_id &&
                company_id == that.company_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, company_id);
    }
}
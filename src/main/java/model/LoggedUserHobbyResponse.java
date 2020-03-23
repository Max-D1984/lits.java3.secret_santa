package model;

import java.util.Objects;

public class LoggedUserHobbyResponse {
    private long hobbyId;
    private String name;

    public LoggedUserHobbyResponse(long hobbyId, String name) {
        this.hobbyId = hobbyId;
        this.name = name;
    }

    public long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggedUserHobbyResponse that = (LoggedUserHobbyResponse) o;
        return hobbyId == that.hobbyId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbyId, name);
    }

    @Override
    public String toString() {
        return "LoggedUserHobbyResponse{" +
                "hobbyId=" + hobbyId +
                ", name='" + name + '\'' +
                '}';
    }
}


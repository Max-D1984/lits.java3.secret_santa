package model;

import java.util.Objects;

public class LoggedUserHobbyResponse {
    private String name;

    public LoggedUserHobbyResponse(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "HobbyList{" +
                "name='" + name + '\'' +
                '}';
    }
}


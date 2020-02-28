package model;

import java.util.Objects;

public class TargetUserPresentResponse {
    private String name;
    private String url;
    private int user_santa_id;

    public TargetUserPresentResponse(String name, String url, int user_santa_id) {
        this.name = name;
        this.url = url;
        this.user_santa_id = user_santa_id;
    }

    public TargetUserPresentResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        TargetUserPresentResponse that = (TargetUserPresentResponse) o;
        return user_santa_id == that.user_santa_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, user_santa_id);
    }

    @Override
    public String toString() {
        return "TargetUserWhishlistPresentResponse{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", user_santa_id=" + user_santa_id +
                '}';
    }
}

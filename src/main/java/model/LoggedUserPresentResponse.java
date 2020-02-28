package model;

import java.util.Objects;

public class LoggedUserPresentResponse {
    private String name;
    private String url;

    public LoggedUserPresentResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public LoggedUserPresentResponse() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggedUserPresentResponse that = (LoggedUserPresentResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "UserWishList{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}

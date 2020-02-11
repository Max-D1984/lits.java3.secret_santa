package model;

import java.util.Objects;

public class MyWishListResponse {
    private String name;
    private String url;
    private String remove;

    public MyWishListResponse(String name, String url, String remove) {
        this.name = name;
        this.url = url;
        this.remove = remove;
    }

    public MyWishListResponse() {
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

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyWishListResponse that = (MyWishListResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(remove, that.remove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, remove);
    }

    @Override
    public String toString() {
        return "MyWishListResponse{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", remove='" + remove + '\'' +
                '}';
    }

}

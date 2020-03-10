package model;

import java.util.Objects;

public class TargetUserPresentResponse {
    private String name;
    private String url;
    private boolean checkd;

    public TargetUserPresentResponse(String name, String url, boolean checkd) {
        this.name = name;
        this.url = url;
        this.checkd = checkd;
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

    public boolean isCheckd() {
        return checkd;
    }

    public void setCheckd(boolean checkd) {
        this.checkd = checkd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetUserPresentResponse that = (TargetUserPresentResponse) o;
        return checkd == that.checkd &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, checkd);
    }

    @Override
    public String toString() {
        return "TargetUserPresentResponse{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", checkd=" + checkd +
                '}';
    }
}

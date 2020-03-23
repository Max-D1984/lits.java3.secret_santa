package model;

import java.util.Objects;

public class TargetUserPresentResponse {
    private long presentId;
    private String name;
    private String url;
    private boolean checked;

    public TargetUserPresentResponse(long presentId, String name, String url, boolean checked) {
        this.presentId = presentId;
        this.name = name;
        this.url = url;
        this.checked = checked;
    }

    public long getPresentId() {
        return presentId;
    }

    public void setPresentId(long presentId) {
        this.presentId = presentId;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetUserPresentResponse that = (TargetUserPresentResponse) o;
        return presentId == that.presentId &&
                checked == that.checked &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentId, name, url, checked);
    }

    @Override
    public String toString() {
        return "TargetUserPresentResponse{" +
                "presentId=" + presentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", checked=" + checked +
                '}';
    }
}

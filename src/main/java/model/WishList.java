package model;

import pojo.Present;

import java.util.List;
import java.util.Objects;

public class WishList {
    private List<Present> presentList;
    private List<Hobby> hobbyList;

    public WishList(List<Present> presentList, List<Hobby> hobbyList) {
        this.presentList = presentList;
        this.hobbyList = hobbyList;
    }

    public WishList() {
    }

    public List<Present> getPresentList() {
        return presentList;
    }

    public void setPresentList(List<Present> presentList) {
        this.presentList = presentList;
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return Objects.equals(presentList, wishList.presentList) &&
                Objects.equals(hobbyList, wishList.hobbyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentList, hobbyList);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "presentList=" + presentList +
                ", hobbyList=" + hobbyList +
                '}';
    }
}

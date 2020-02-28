package model;

import java.util.List;
import java.util.Objects;

public class LoggedUserWishListResponse {
    private List<LoggedUserPresentResponse> loggedUserPresentResponseList;
    private List<LoggedUserHobbyResponse> loggedUserHobbyResponseList;

    public LoggedUserWishListResponse(List<LoggedUserPresentResponse> loggedUserPresentResponseList, List<LoggedUserHobbyResponse> loggedUserHobbyResponseList) {
        this.loggedUserPresentResponseList = loggedUserPresentResponseList;
        this.loggedUserHobbyResponseList = loggedUserHobbyResponseList;
    }

    public LoggedUserWishListResponse() {
    }

    public List<LoggedUserPresentResponse> getLoggedUserPresentResponseList() {
        return loggedUserPresentResponseList;
    }

    public void setLoggedUserPresentResponseList(List<LoggedUserPresentResponse> loggedUserPresentResponseList) {
        this.loggedUserPresentResponseList = loggedUserPresentResponseList;
    }

    public List<LoggedUserHobbyResponse> getLoggedUserHobbyResponseList() {
        return loggedUserHobbyResponseList;
    }

    public void setLoggedUserHobbyResponseList(List<LoggedUserHobbyResponse> loggedUserHobbyResponseList) {
        this.loggedUserHobbyResponseList = loggedUserHobbyResponseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggedUserWishListResponse loggedUserWishListResponse = (LoggedUserWishListResponse) o;
        return Objects.equals(loggedUserPresentResponseList, loggedUserWishListResponse.loggedUserPresentResponseList) &&
                Objects.equals(loggedUserHobbyResponseList, loggedUserWishListResponse.loggedUserHobbyResponseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedUserPresentResponseList, loggedUserHobbyResponseList);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "presentList=" + loggedUserPresentResponseList +
                ", hobbyList=" + loggedUserHobbyResponseList +
                '}';
    }
}

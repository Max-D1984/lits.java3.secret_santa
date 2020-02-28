package model;

import java.util.List;
import java.util.Objects;

public class TargetUserWishListResponse {
    private List<TargetUserPresentResponse> targetUserPresentResponseList;
    private List<TargetUserHobbyResponse> targetUserHobbyResponseList;

    public TargetUserWishListResponse(List<TargetUserPresentResponse> targetUserPresentResponseList, List<TargetUserHobbyResponse> targetUserHobbyResponseList) {
        this.targetUserPresentResponseList = targetUserPresentResponseList;
        this.targetUserHobbyResponseList = targetUserHobbyResponseList;
    }

    public TargetUserWishListResponse() {
    }

    public List<TargetUserPresentResponse> getTargetUserPresentResponseList() {
        return targetUserPresentResponseList;
    }

    public void setTargetUserPresentResponseList(List<TargetUserPresentResponse> targetUserPresentResponseList) {
        this.targetUserPresentResponseList = targetUserPresentResponseList;
    }

    public List<TargetUserHobbyResponse> getTargetUserHobbyResponseList() {
        return targetUserHobbyResponseList;
    }

    public void setTargetUserHobbyResponseList(List<TargetUserHobbyResponse> targetUserHobbyResponseList) {
        this.targetUserHobbyResponseList = targetUserHobbyResponseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetUserWishListResponse that = (TargetUserWishListResponse) o;
        return Objects.equals(targetUserPresentResponseList, that.targetUserPresentResponseList) &&
                Objects.equals(targetUserHobbyResponseList, that.targetUserHobbyResponseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetUserPresentResponseList, targetUserHobbyResponseList);
    }

    @Override
    public String toString() {
        return "TargetUserWishListResponse{" +
                "targetUserPresentResponseList=" + targetUserPresentResponseList +
                ", targetUserHobbyResponseList=" + targetUserHobbyResponseList +
                '}';
    }
}

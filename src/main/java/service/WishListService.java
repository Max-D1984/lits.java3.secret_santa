package service;

import model.*;

import java.util.List;

public interface WishListService {
    List<LoggedUserPresentResponse> loggedUserPresentResponse(int loggedUserId);
    List<LoggedUserHobbyResponse> loggedUserHobbyResponse(int loggedUserId);
    List<TargetUserPresentResponse> targetUserPresentResponse(int targetUserId);
    List<TargetUserHobbyResponse> targetUserHobbyResponse(int targetUserId);
    LoggedUserWishListResponse loggeUserWishListResponse (int loggedUserId);
    TargetUserWishListResponse targetUserWishListResponse (int targetUserId);
    void addPresentToWishlist(String presentName, String presentURl, int loggedUserId);
}

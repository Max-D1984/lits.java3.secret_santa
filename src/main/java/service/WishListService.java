package service;

import model.*;

import java.util.List;

public interface WishListService {
    List<LoggedUserPresentResponse> loggedUserPresentResponse(int loggedUserId);
    List<LoggedUserHobbyResponse> loggedUserHobbyResponse(int loggedUserId);
    List<TargetUserPresentResponse> targetUserPresentResponse(int targetUserId, int loggedUserId);
    List<TargetUserHobbyResponse> targetUserHobbyResponse(int targetUserId);
    LoggedUserWishListResponse loggeUserWishListResponse (int loggedUserId);
    TargetUserWishListResponse targetUserWishListResponse (int targetUserId, int loggedUserId);
    void addPresentToWishlist(int loggedUserId, int presentId);
    void deletePresentFromWishlist(int loggedUserId, int presentId);

}

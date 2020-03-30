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
    void addPresentToWishlist(String presentName, String presentURl, int loggedUserId);
    void checkUncheckTargetPresent (int loggedUser, int targetUser, int presentId, boolean check);

}

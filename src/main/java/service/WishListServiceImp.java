package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Hobby;
import pojo.Present;
import pojo.UserToHobby;
import pojo.UserToPresent;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImp implements WishListService {

    private UserToPresentService userToPresentService = new UserToPresentServiceImpl();
    private PresentServiceImpl presentService = new PresentServiceImpl();
    private UserToHobbyService userToHobbyService = new UserToHobbyServiceImpl();
    private HobbyServiceImpl hobbyService = new HobbyServiceImpl();



    public List<TargetUserPresentResponse> targetUserPresentResponse(int targetUserId, int loggedUserId) {
        try {
            List<Integer> usersPresentsListId = userToPresentService.readByUser(targetUserId).stream()
                    .map(y -> y.getPresentId())
                    .collect(Collectors.toList());
            List<UserToPresent> userToPresentList = userToPresentService.readByUser(targetUserId);
            List<Present> presentList = presentService.readListByPresentsId(usersPresentsListId);
            List<TargetUserPresentResponse> targetUserPresentResponses = new LinkedList<>();
boolean presentCheckedByUser = false;
            for (int i = 0; i < presentList.size(); i++) {
                if(userToPresentList.get(i).getUser_santa_id()==0 || userToPresentList.get(i).getUser_santa_id() == loggedUserId) {
                    if(userToPresentList.get(i).getUser_santa_id() == loggedUserId){
                        presentCheckedByUser = true;
                    }else{
                        presentCheckedByUser = false;

                    }
                    targetUserPresentResponses.add(new TargetUserPresentResponse(presentList.get(i).getName(), presentList.get(i).getUrl(), presentCheckedByUser));
                }

            }
            return targetUserPresentResponses;
        } catch (Exception ex) {
            System.out.println("Something was wrong");
            return null;
        }
    }

    public List<TargetUserHobbyResponse> targetUserHobbyResponse(int targetUserId) {
                try {
            List<Integer> usersHobbyListId = userToHobbyService.readListByUserId(targetUserId).stream()
                    .map(y -> y.getHobby_id())
                    .collect(Collectors.toList());
            List<UserToHobby> userToHobbyList = userToHobbyService.readListByUserId(targetUserId);
            List<Hobby> hobbyList = hobbyService.readListByHobbysId(usersHobbyListId);
            List<TargetUserHobbyResponse> targetUserHobbyResponses = new LinkedList<>();

            for (int i = 0; i < hobbyList.size(); i++) {
                targetUserHobbyResponses.add(new TargetUserHobbyResponse(hobbyList.get(i).getName(), userToHobbyList.get(i).getUser_santa_id()));
            }
            return targetUserHobbyResponses;
        } catch (Exception ex) {
            System.out.println("Somthing was wrong");
            return null;
        }
    }

    public List<LoggedUserPresentResponse> loggedUserPresentResponse(int loggedUserId) {

        try {
            List<Integer> usersPresentsListId = userToPresentService.readByUser(loggedUserId).stream()
                    .map(y -> y.getPresentId())
                    .collect(Collectors.toList());
            List<LoggedUserPresentResponse> loggedUserPresentResponses = presentService.readListByPresentsId(usersPresentsListId).stream()
                    .map(present -> new LoggedUserPresentResponse(present.getName(), present.getUrl()))
                    .collect(Collectors.toList());
            return loggedUserPresentResponses;
        } catch (Exception ex) {
            System.out.println("Something was wrong");
            return null;
        }
    }

    public List<LoggedUserHobbyResponse> loggedUserHobbyResponse(int loggedUserId) {
        try {
            List<Integer> usersHobbyListId = userToHobbyService.readListByUserId(loggedUserId).stream()
                    .map(y -> y.getHobby_id())
                    .collect(Collectors.toList());
            List<UserToHobby> userToHobbyList = userToHobbyService.readListByUserId(loggedUserId);
            List<Hobby> hobbyList = hobbyService.readListByHobbysId(usersHobbyListId);
            List<LoggedUserHobbyResponse> loggedUserHobbyResponses = hobbyService.readListByHobbysId(usersHobbyListId).stream()
                    .map(hobby -> new LoggedUserHobbyResponse(hobby.getName()))
                    .collect(Collectors.toList());
            return loggedUserHobbyResponses;
        } catch (Exception ex) {
            System.out.println("Somthing was wrong in userHobbyResponse");
            return null;
        }
    }

    public LoggedUserWishListResponse loggeUserWishListResponse (int loggedUserId){
       return new LoggedUserWishListResponse(loggedUserPresentResponse(loggedUserId), loggedUserHobbyResponse(loggedUserId));

    }

    public TargetUserWishListResponse targetUserWishListResponse (int targetUserId, int loggedUserId){
        return new TargetUserWishListResponse(targetUserPresentResponse(targetUserId, loggedUserId), targetUserHobbyResponse(targetUserId));
    }

    @Override
    public void addPresentToWishlist(String presentName, String presentURl, int loggedUserId) {
        try {
            presentService.readIdByNameAndURL(presentName, presentURl);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}


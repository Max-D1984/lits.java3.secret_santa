package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Hobby;
import pojo.Present;
import pojo.UserToHobby;
import pojo.UserToPresent;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImp implements WishListService {
    @Autowired
    private UserToPresentService userToPresentService;
    @Autowired
    private PresentServiceImpl presentService;
    @Autowired
    private UserToHobbyService userToHobbyService;
    @Autowired
    private HobbyServiceImpl hobbyService;


    private UserToPresentService getUserToPresentService() {
        return userToPresentService;
    }

    private PresentServiceImpl getPresentService() {
        return presentService;
    }

    private UserToHobbyService getUserToHobbyService() {
        return userToHobbyService;
    }

    private HobbyServiceImpl getHobbyService() {
        return hobbyService;
    }

    public List<TargetUserPresentResponse> targetUserPresentResponse(int targetUserId) {
        try {
            List<Integer> usersPresentsListId = userToPresentService.readByUser(targetUserId).stream()
                    .map(y -> y.getPresentId())
                    .collect(Collectors.toList());
            List<UserToPresent> userToPresentList = userToPresentService.readByUser(targetUserId);
            List<Present> presentList = presentService.readListByPresentsId(usersPresentsListId);
            List<TargetUserPresentResponse> targetUserPresentResponses = new LinkedList<>();

            for (int i = 0; i < presentList.size(); i++) {
                targetUserPresentResponses.add(new TargetUserPresentResponse(presentList.get(i).getName(), presentList.get(i).getUrl(), userToPresentList.get(i).getUser_santa_id()));
            }
            return targetUserPresentResponses;
        } catch (Exception ex) {
            System.out.println("Something was wrong");
            return null;
        }
    }

    public List<TargetUserHobbyResponse> targetUserHobbyResponse(int targetUserId) {
        try {
            List<Integer> usersHobbyListId = userToHobbyService.readListByUserId(2).stream()
                    .map(y -> y.getHobby_id())
                    .collect(Collectors.toList());
            List<UserToHobby> userToHobbyList = userToHobbyService.readListByUserId(2);
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
            List<Integer> usersHobbyListId = userToHobbyService.readListByUserId(2).stream()
                    .map(y -> y.getHobby_id())
                    .collect(Collectors.toList());
            List<UserToHobby> userToHobbyList = userToHobbyService.readListByUserId(2);
            List<Hobby> hobbyList = hobbyService.readListByHobbysId(usersHobbyListId);
            List<LoggedUserHobbyResponse> loggedUserHobbyResponses = hobbyService.readListByHobbysId(usersHobbyListId).stream()
                    .map(hobby -> new LoggedUserHobbyResponse(hobby.getName()))
                    .collect(Collectors.toList());
            return loggedUserHobbyResponses;
        } catch (Exception ex) {
            System.out.println("Somthing was wrong");
            return null;
        }
    }

    public LoggedUserWishListResponse loggeUserWishListResponse (int loggedUserId){
       return new LoggedUserWishListResponse(loggedUserPresentResponse(loggedUserId), loggedUserHobbyResponse(loggedUserId));

    }

    public TargetUserWishListResponse targetUserWishListResponse (int targetUserId){
        return new TargetUserWishListResponse(targetUserPresentResponse(targetUserId), targetUserHobbyResponse(targetUserId));
    }

    @Override
    public void addPresentToWishlist(String presentName, String presentURl, int loggedUserId){
        try {
            presentService.readIdByNameAndURL(presentName, presentURl);
        }catch (Exception ex){
            System.out.println(ex);
        }
       //     userToPresentService.create(new UserToPresent(0, loggedUserId,present.getId()));




    }

}


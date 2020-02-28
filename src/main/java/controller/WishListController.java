package controller;


import model.LoggedUserPresentResponse;
import org.springframework.beans.factory.annotation.Autowired;


import service.UserToHobbyService;
import service.UserToPresentService;

import java.util.List;


public class WishListController {

    private int loggedInUserId = 6;

    @Autowired
    public UserToHobbyService getUserToHobbyService() {
        return userToHobbyService;
    }
    @Autowired
    private UserToHobbyService userToHobbyService;
    @Autowired
    public UserToPresentService getUserToPresentService() {
        return userToPresentService;
    }
    @Autowired
    private UserToPresentService userToPresentService;

    public List<LoggedUserPresentResponse> getLoggetUserPresent (Integer id){
        userToPresentService.readPresentListById(id);

return null ;
    }



}
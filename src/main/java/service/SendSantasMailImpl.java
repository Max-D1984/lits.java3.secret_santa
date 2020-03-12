package service;


import controller.MailController;

public class SendSantasMailImpl implements SendSantasMail {
    UserTargetService userTargetService = new UserTargetServiceImpl();
    UserService userService = new UserServiceImpl();
    MailController mailController =new MailController();

    @Override
    public void sendMailToSantas(Integer companyId) {
       // userTargetService.getSantasInCompany(1).stream().forEach(y-> mailController.sendEmail(userService.readUser(y).getEmail(), ));

        userTargetService.getSantasInCompany(1).stream().forEach(y-> System.out.println(userService.readUser(y).getEmail()));
    }
}

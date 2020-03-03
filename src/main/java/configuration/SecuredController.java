package configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/secured")
public class SecuredController {

    @RequestMapping(value = "/initial")
    public String initial(){
        return "Got it!";
    }
}

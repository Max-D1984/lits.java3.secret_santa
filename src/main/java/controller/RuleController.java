package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Rule;
import service.RuleService;
import service.RuleServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/rule")

public class RuleController {

    private RuleService ruleService = new RuleServiceImpl();

//    @Autowired
//    private RuleService ruleService;
//
//    public RuleService getRuleService() {return ruleService;
//    }


//    @RequestMapping(
//            value = "/my-rule",
//            method = RequestMethod.GET)
    public ResponseEntity getRule(
            @RequestParam Integer id){
        return ResponseEntity.of(Optional.of(
                ruleService.read(id)));
    }
//    @RequestMapping(
//            value = "/my-rule/list",
//            method = RequestMethod.GET)
    public ResponseEntity getRuleList(){
        return ResponseEntity.of(Optional.of(ruleService.readList()));
    }
//    @RequestMapping(
//            value = "/my-rule",
//            method = RequestMethod.POST)
    public ResponseEntity postRule(){
        return ResponseEntity.of(Optional.of(
                new Rule(1,1,"new Rule", Date.valueOf("2019-12-24"),300)));
    }
//    @RequestMapping(
//            value = "/my-rule",
//            method = RequestMethod.PUT)
    public ResponseEntity putRule(){
        return ResponseEntity.of(Optional.of(
                new Rule(1,1,"new Rule", Date.valueOf("2019-12-24"),300)));
    }
//    @RequestMapping(
//            value = "/my-rule",
//            method = RequestMethod.DELETE)
    public ResponseEntity deleteRule(
            @RequestParam Integer id){
        return ResponseEntity.of(Optional.of(
                new Rule(1,1,"new Rule", Date.valueOf("2019-12-24"),300)));
    }
}

package controller;

import PM.PM;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class DemoController {

    @RequestMapping(value="/index", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String index(){
        return "index";
    }


    @RequestMapping(value="pm.do", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String get_pm(){
        PM pm = new PM();
        return pm.toString();
    }

    @RequestMapping(value="test", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String test(){
        PM pm = new PM();
        return pm.toString();
    }

    @ResponseBody
    @RequestMapping(value="login", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String pswd){
        if( username.equals("tiancheng") && pswd.equals("137640")){
            return "login success";
        }
        return "login fail";
    }
}

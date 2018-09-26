package controller;

import hulu.PM;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class DemoController {

    @RequestMapping(value="/index", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String index(){
        PM pm = new PM();
        return pm.toString();
    }

}

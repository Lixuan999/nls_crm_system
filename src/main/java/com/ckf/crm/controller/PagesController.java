package com.ckf.crm.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 20:21
 */

@Api(tags = "页面跳转管理")
@Controller
public class PagesController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/index")
    public String index() {
        return "index";

    }

    @RequestMapping("/rec")
    public String recommend() {
        return "recommend";

    }



    @GetMapping("/personal")
    public String personal() {
        return "adminInfo";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/cut")
    public String noRights() {
        return "login";
    }

}

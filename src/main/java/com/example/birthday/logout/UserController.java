//package com.example.birthday.logout;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(path = "/user")
//public class UserController {
//
//    private final UserCache userCache;
//
//    public UserController(UserCache userCache) {
//        this.userCache = userCache;
//    }
//
//    @GetMapping(path = "/language")
//    @ResponseBody
//    public String getLanguage() {
//        Object UserUtils;
//        String userName = UserUtils.getAuthenticatedUserName();
//        User user = userCache.getByUserName(userName);
//    }
//}
//

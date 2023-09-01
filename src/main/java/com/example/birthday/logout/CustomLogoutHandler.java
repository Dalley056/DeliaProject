//package com.example.birthday.logout;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomLogoutHandler implements LogoutHandler {
//
//    private final UserCache userCache;
//
//    public CustomLogoutHandler(UserCache userCache) {
//        this.userCache = userCache;
//    }
//
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response,
//                       Authentication authentication) {
//        String userName = UserUtils.getAuthenticatedUserName();
//        userCache.evictUser(userName);
//    }
//}
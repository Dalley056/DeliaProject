//package com.example.birthday.logout;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//@Service
//public class UserCache {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final ConcurrentMap<String, User> store = new ConcurrentHashMap<>(256);
//    public User getByUserName(String userName) {
//        return store.computeIfAbsent(userName, k ->
//                entityManager.createQuery("from User where login=:login", User.class)
//                        .setParameter("login", k)
//                        .getSingleResult());
//    }
//    public void evictUser(String userName) {
//        store.remove(userName);
//    }
//
//}

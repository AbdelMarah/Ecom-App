package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/users"})
    public Iterable<User> getUsers() {
        System.out.println("test");
        return userService.getUsers();
    }

    /*
    @PostMapping({"/registerNewUser"})
    public ResponseEntity registerNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerNewUser(user));
    }

    @PostMapping("/api/foos")
    @ResponseBody
    public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
    }
    => http://localhost:8080/api/foos?id=abc
    */

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    //@PostAuthorize("hasAuthority('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping("/profile")
    public User getProfile(Principal principal){
        return userService.getProfile(principal.getName());
    }

    @GetMapping("/getName")
    public String getName(Principal principal){
        return userService.getName(principal.getName());
    }
}

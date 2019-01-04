package com.Madhatter.MadHatter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.payloads.UserProfile;
import com.Madhatter.MadHatter.security.CurrentUser;
import com.Madhatter.MadHatter.security.UserPrincipal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/user-profile")
    public UserProfile getUserProfile(@CurrentUser UserPrincipal currentUser) {
        return new UserProfile(currentUser.getId(), currentUser.getName(), currentUser.getUsername(),
                currentUser.getEmail(), currentUser.getAuthorities().iterator().next().toString());
    }
}
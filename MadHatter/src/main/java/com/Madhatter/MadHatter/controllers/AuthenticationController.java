package com.Madhatter.MadHatter.controllers;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.RoleRepository;
import com.Madhatter.MadHatter.Repositories.UserRepository;
import com.Madhatter.MadHatter.models.Role;
import com.Madhatter.MadHatter.models.User;
import com.Madhatter.MadHatter.payloads.APIResponse;
import com.Madhatter.MadHatter.payloads.AppException;
import com.Madhatter.MadHatter.payloads.JwtAuthenticationResponse;
import com.Madhatter.MadHatter.payloads.LoginRequest;
import com.Madhatter.MadHatter.payloads.SignUpRequest;
import com.Madhatter.MadHatter.security.JwtTokenProvider;




@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        JwtTokenProvider tokenProvider;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(authentication);
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }

        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity<>(new APIResponse(false, "Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity<>(new APIResponse(false, "Email Address already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                Role userRole = roleRepository.findByName(signUpRequest.getRoleName())
                                .orElseThrow(() -> new AppException("User Role not set."));

                user.setRoles(Collections.singleton(userRole));
                userRepository.save(user);

                return ResponseEntity.ok().body(new APIResponse(true, "User registered successfully"));
        }
        
        @PutMapping("/updateUser")
        public ResponseEntity<?> updateUserInformation(@RequestBody User user) {
        	
        	userRepository.save(user);
			return null;
        	
        }
}
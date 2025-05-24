package com.example.taggo.domain.user.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taggo.domain.user.api.request.LoginRequest;
import com.example.taggo.domain.user.api.request.RegisterRequest;
import com.example.taggo.domain.user.api.response.LoginResponse;
import com.example.taggo.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request){
        userService.register(request);
        return ResponseEntity.status(CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        return ResponseEntity.status(OK).body(response);
    }
//    TO_DO 레디스 적용 후, 토큰 관리 로직 적용 후 오픈
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request){
//        userService.logout(request);
//        return ResponseEntity.ok().build();
//    }
}

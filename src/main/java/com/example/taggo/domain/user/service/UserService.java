package com.example.taggo.domain.user.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taggo.domain.common.exception.BaseException;
import com.example.taggo.domain.common.exception.ErrorType;
import com.example.taggo.domain.common.util.JwtUtil;
import com.example.taggo.domain.user.api.request.LoginRequest;
import com.example.taggo.domain.user.api.request.LogoutRequest;
import com.example.taggo.domain.user.api.request.RegisterRequest;
import com.example.taggo.domain.user.api.response.TokenResponse;
import com.example.taggo.domain.user.model.User;
import com.example.taggo.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;
    private final StringRedisTemplate redisTemplate;
    private final long refreshTokenValidity = 1000L * 60 * 60 * 24 * 7; // 7일
    private final long accessTokenValidity = 1000L * 60 * 60; // 1시간

    @Transactional
    public void register(RegisterRequest request){
        if(userRepository.existsByEmail((request.email()))){
            throw new BaseException(ErrorType.DUPLICATED_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        userRepository.save(
                User.create(
                        request.name(),
                        request.email(),
                        encodedPassword
                )
        );
    }

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));

        String password = request.password();
        String encodedPassword = user.getPassword();

        if(!passwordEncoder.matches(password, encodedPassword)){
            throw new BaseException(ErrorType.OUTMATCHED_PASSWORD);
        }

        // AccessToken, RefreshToken 발급
        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        // RefreshToken Redis에 저장 (key: user email, value: refreshToken)
        redisTemplate.opsForValue().set("refresh:" + user.getEmail(), refreshToken, refreshTokenValidity, TimeUnit.MILLISECONDS);

        return new TokenResponse(accessToken, refreshToken);
    }

    public void logout(LogoutRequest request){
        // AccessToken, RefreshToken 블랙리스트 처리
        String accessToken = request.getAccessToken();
        String refreshToken = request.getRefreshToken();
        if (accessToken != null) {
            redisTemplate.opsForValue().set("blacklist:access:" + accessToken, "logout", accessTokenValidity, TimeUnit.MILLISECONDS);
        }
        if (refreshToken != null) {
            String email = jwtUtil.getEmailFromToken(refreshToken);
            redisTemplate.delete("refresh:" + email);
            redisTemplate.opsForValue().set("blacklist:refresh:" + refreshToken, "logout", refreshTokenValidity, TimeUnit.MILLISECONDS);
        }
    }

    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));
    }
}

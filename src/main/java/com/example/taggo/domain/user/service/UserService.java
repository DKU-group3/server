package com.example.taggo.domain.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taggo.domain.common.exception.BaseException;
import com.example.taggo.domain.common.exception.ErrorType;
import com.example.taggo.domain.user.UserJwtTokenProvider;
import com.example.taggo.domain.user.api.request.LoginRequest;
import com.example.taggo.domain.user.api.request.LogoutRequest;
import com.example.taggo.domain.user.api.request.RegisterRequest;
import com.example.taggo.domain.user.api.response.LoginResponse;
import com.example.taggo.domain.user.model.User;
import com.example.taggo.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserJwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));

        String password = request.password();
        String encodedPassword = user.getPassword();

        if(!passwordEncoder.matches(password, encodedPassword)){
            throw new BaseException(ErrorType.OUTMATCHED_PASSWORD);
        }

        // JWT AccessToken, RefreshToken 발급
        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(user.getId()));
        String refreshToken = jwtTokenProvider.createRefreshToken(String.valueOf(user.getId()));
        // TODO: RefreshToken을 Redis 등 외부 저장소에 저장
        return new LoginResponse(accessToken, refreshToken);
    }

    public void logout(LogoutRequest request){
        // TODO: RefreshToken을 Redis 등에서 삭제(블랙리스트 처리)
        // TODO: 필요시 AccessToken도 블랙리스트 처리
    }

    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));
    }
}

package com.example.taggo.domain.user.service;

import com.example.taggo.domain.common.exception.BaseException;
import com.example.taggo.domain.common.exception.ErrorType;
import com.example.taggo.domain.user.api.request.LoginRequest;
import com.example.taggo.domain.user.api.request.LogoutRequest;
import com.example.taggo.domain.user.api.request.RegisterRequest;
import com.example.taggo.domain.user.model.User;
import com.example.taggo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
    public void login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));

        String password = request.password();
        String encodedPassword = user.getPassword();

        if(!passwordEncoder.matches(password, encodedPassword)){
            throw new BaseException(ErrorType.OUTMATCHED_PASSWORD);
        }

        // To-DO JWT AccessToken, RefreshToken 발급 후 전송
    }

    public void logout(LogoutRequest request){
        // To-Do RefreshToken 블랙리스트로 레디스에 추가, 레디스에 저장되어 있던 AccesToken 폐기
    }

    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorType.NOTFOUND_USER));
    }
}

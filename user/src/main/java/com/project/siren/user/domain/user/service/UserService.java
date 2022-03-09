package com.project.siren.user.domain.user.service;

import com.project.siren.user.domain.user.domain.User;
import com.project.siren.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(User user) {
        // 패스워드 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}

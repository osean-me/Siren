package com.project.siren.user.domain.user.service;

import com.project.siren.user.domain.user.domain.User;
import com.project.siren.user.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mockito 를 이용한 단위 테스트를 위해 선언한다.
class UserServiceTest {

    // @Mock > 자동으로 Mock 객체를 주입해주지만, Interface 의 구현체 중 선택해야 한다면 아래의 방식을 이용한다.
    private final UserRepository  userRepository  = Mockito.mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private       UserService     userService;

    @BeforeEach
    public void beforeEach() {
        this.userService = new UserService(userRepository, passwordEncoder);
    }


    @Test
    @DisplayName("회원가입")
    public void join() {
        // Given
        User user = User.builder()
                .email("test@email.com")
                .contact("01012345678")
                .password("TEST0123456789!!")
                .nickname("테스트 유저")
                .build();

        // When
        when(userService.join(user)).then(returnsFirstArg());
        User newUser = userService.join(user);

        // Then
        assertAll(
                () -> assertEquals(user.getEmail(), newUser.getEmail()),
                () -> assertEquals(user.getContact(), newUser.getContact()),
                () -> assertEquals(user.getNickname(), newUser.getNickname())
        );
    }
}
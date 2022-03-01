package com.project.siren.user.domain.user.dto.request;

import com.project.siren.user.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinUserRequest {
    private String email;
    private String password;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .build();
    }
}

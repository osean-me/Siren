package com.project.siren.user.domain.user.dto.response;

import com.project.siren.user.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinUserResponse {

    private User user;

    public static JoinUserResponse toResonse(User user) {
        return new JoinUserResponse(user);
    }
}

package com.project.siren.user.domain.user.dto.request;

import com.project.siren.user.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JoinUserRequest {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 양식에 맞게 입력해주세요.")
    private String email;

    @NotNull(message = "패스워드를 입력해주세요.")
    @Length(min = 12, message = "비밀번호는 12자 이상 입력해주세요.")
    private String password;

    @NotNull(message = "닉네임을 입력해주세요.")
    @Length(min = 3, max = 8, message = "닉네임은 3자 이상 8자 이하로 입력해주세요.")
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .build();
    }
}

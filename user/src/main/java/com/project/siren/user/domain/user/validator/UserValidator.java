package com.project.siren.user.domain.user.validator;

import com.project.siren.user.domain.user.dto.request.JoinUserRequest;
import com.project.siren.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinUserRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        JoinUserRequest joinUserRequest = (JoinUserRequest) target;
        boolean         hasUser         = userService.findByEmail(joinUserRequest.getEmail()).isEmpty();
        if (hasUser) {
            errors.rejectValue("email", "Duplicate", "중복된 이메일이 존재합니다. 다시 확인해주세요.");
        }
    }
}

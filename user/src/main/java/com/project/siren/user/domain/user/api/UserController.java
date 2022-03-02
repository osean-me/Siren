package com.project.siren.user.domain.user.api;

import com.project.siren.user.domain.user.domain.User;
import com.project.siren.user.domain.user.dto.request.JoinUserRequest;
import com.project.siren.user.domain.user.dto.response.JoinUserResponse;
import com.project.siren.user.domain.user.service.UserService;
import com.project.siren.user.domain.user.validator.UserValidator;
import com.project.siren.user.global.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService   userService;
    private final UserValidator userValidator;

    /**
     * 회원가입 핸들러
     *
     * @param joinUserRequest JoinUserRequest
     * @return JoinUserResponse
     */
    @PostMapping
    public ResponseEntity<BasicResponse<JoinUserResponse>> join(@RequestBody @Valid JoinUserRequest joinUserRequest,
                                                                BindingResult bindingResult) throws BindException {
        userValidator.validate(joinUserRequest, bindingResult);
        if (bindingResult.hasErrors()) throw new BindException(bindingResult);
        User             user             = userService.join(joinUserRequest.toEntity());
        JoinUserResponse joinUserResponse = JoinUserResponse.toResonse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(BasicResponse.create(true, joinUserResponse));
    }
}

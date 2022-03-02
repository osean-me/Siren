package com.project.siren.user.domain.user.api;

import com.project.siren.user.domain.user.domain.User;
import com.project.siren.user.domain.user.dto.request.JoinUserRequest;
import com.project.siren.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<User> join(@RequestBody JoinUserRequest userRequest) {
        User user = userRequest.toEntity();
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}

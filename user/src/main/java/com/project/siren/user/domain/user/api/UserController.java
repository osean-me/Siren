package com.project.siren.user.domain.user.api;

import com.project.siren.user.domain.user.domain.User;
import com.project.siren.user.domain.user.dto.request.JoinUserRequest;
import com.project.siren.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public ResponseEntity<User> join(@RequestParam JoinUserRequest userRequest) {
        User user = userRequest.toEntity();
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}

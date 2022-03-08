package com.project.siren.user.domain.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.siren.user.domain.user.dto.request.JoinUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc      mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("회원가입 - 성공, 201(CREATED) 반환")
    public void join_success() throws Exception {
        // Given
        JoinUserRequest joinUserRequest = JoinUserRequest.builder()
                .email("test@email.com")
                .contact("01012345678")
                .password("TEST0123456789!!")
                .nickname("테스트 유저")
                .build();

        String requestJson = mapper.writeValueAsString(joinUserRequest);

        // When
        mockMvc.perform(post("/v1/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                // Then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("success").value(true))
                .andExpect(jsonPath("body.user.id").isNotEmpty())
                .andExpect(jsonPath("body.user.email").value(joinUserRequest.getEmail()))
                .andExpect(jsonPath("body.user.contact").value(joinUserRequest.getContact()))
                .andExpect(jsonPath("body.user.nickname").value(joinUserRequest.getNickname()));
    }

    @Test
    @DisplayName("회원가입 - 실패, 4xx 반환")
    public void join_fail() throws Exception {
        // Given
        JoinUserRequest joinUserRequest = JoinUserRequest.builder()
                .build();

        String requestJson = mapper.writeValueAsString(joinUserRequest);

        // When
        mockMvc.perform(post("/v1/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                // Then
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
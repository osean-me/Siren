package com.project.siren.user.domain.user.domain;

import com.project.siren.core.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
@Getter
@ToString(of = {"id", "email", "nickname"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}

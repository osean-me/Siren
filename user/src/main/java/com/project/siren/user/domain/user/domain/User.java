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

    @Column(name = "email", updatable = false, nullable = false, unique = true)
    private String email;

    @Column(name = "contact", nullable = false, unique = true)
    private String contact;

    @Setter // 패스워드 암호화 된 값을 지정하기 위한 @Setter 설정
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Builder
    protected User(String email, String contact, String password, String nickname) {
        this.id = null;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.nickname = nickname;
    }
}

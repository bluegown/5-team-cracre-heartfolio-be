package com.heartfoilo.demo.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 28)
    private String name;

    @Column(nullable = false, length = 32)
    private String email;

    @Column(nullable = false, length = 28)
    private String nickname;

    private String profile;
}
package com.heartfoilo.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되는 기본 키 설정
    @Column(name = "user_id") // 데이터베이스 컬럼과 매핑
    private Long userId;

    @Column(nullable = false, length = 28)
    private String name;

    @Column(nullable = false, length = 32)
    private String email;

    @Column(nullable = false, length = 28)
    private String nickname;

    @Column(length = 255)
    private String field;
}
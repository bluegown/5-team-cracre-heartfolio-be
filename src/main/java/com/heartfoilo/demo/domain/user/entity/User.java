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
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 28)
    private String name;


    @Column(nullable = false, length = 28)
    private String nickname;


    public User(String name,String nickname){
        this.name = name;
        this.nickname = nickname;
    }





    // private String profile;
}
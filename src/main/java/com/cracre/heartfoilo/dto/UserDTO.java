package com.cracre.heartfoilo.dto;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String nickname;
    private String field;
}
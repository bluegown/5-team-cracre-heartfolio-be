package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock; // Stock과 다대일 관계
}
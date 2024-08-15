package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "fortune")
public class Fortune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fortuneId;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private Long cash;
}
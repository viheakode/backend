package com.viheakode.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String uuid;
    private String name;
    private String email;
    private String message;
    private String appName;
    private LocalDateTime createdAt;
}

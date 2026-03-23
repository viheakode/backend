package com.viheakode.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    private String uuid;
    @Column(nullable = false, unique = true)
    private String permissionName;
    private String description;
    private String status;
    private String publisher;
    private LocalDateTime publishedDate;
    private LocalDateTime modifiedDate;

    public Permission(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
}

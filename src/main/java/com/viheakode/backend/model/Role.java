package com.viheakode.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String uuid;
    @Column(nullable = false, unique = true)
    private String roleName;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    private String status;
    private String publisher;
    private LocalDateTime publishedDate;
    private LocalDateTime modifiedDate;

    public Role(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
}

package com.viheakode.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tblCategory")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public Category() {
        this.status = "1";
        this.publisher = "s.admin";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}

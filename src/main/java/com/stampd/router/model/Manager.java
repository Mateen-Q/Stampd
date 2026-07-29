package com.stampd.router.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slackId;
    private String department;
}

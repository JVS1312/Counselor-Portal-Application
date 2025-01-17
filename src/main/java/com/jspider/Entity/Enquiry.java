package com.jspider.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspider.enums.ClassMode;
import com.jspider.enums.Courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer eid;

    private String name;

    @Column(unique = true)
    private String email;

    private Long phone;

    @Enumerated(EnumType.STRING)
    private Courses course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassMode classMode = ClassMode.OFFLINE;

    private Double fees;

    @ManyToOne
    @JoinColumn(name = "cid") 
    @JsonIgnore
    private Counselor counselor;
}
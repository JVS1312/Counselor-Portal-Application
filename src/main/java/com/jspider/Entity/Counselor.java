package com.jspider.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jspider.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Counselor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private Long phone;

    @OneToMany(mappedBy = "counselor", cascade = CascadeType.ALL) 
    private List<Enquiry> enquiry;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
package com.example.asigantura.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 63, min = 2, message = "Name must be between 2 and 63 characters")
    @Column(name = "subject_name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indicates the owner side of the relationship
    private List<SubjectTeacher> studentSubjects = new ArrayList<>();

    // Automatically set createdAt before persisting
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<SubjectTeacher> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<SubjectTeacher> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
}

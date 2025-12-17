package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long id;

    @Column(name="name",length=20,nullable=false)
    private String name;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(
        name="student_course",
        joinColumns=@JoinColumn(name="stduent_id"),
        inverseJoinColumns=@JoinColumn(name="course_id")
    )
    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();
}

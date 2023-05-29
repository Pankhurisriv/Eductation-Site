package com.example.student.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = -3770142805983192214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long courseId;
    @Schema(
            description = "Courses Name"
    )
    @Column(name = "COURSE_NAME")
    private String courseName;


    @Schema(
            description = "Course Details"
    )
    @Column(name = "COURSE_DETAILS")
    private String courseDetails;

    @Schema(
            description = "Enrolled Students"
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", joinColumns = {
            @JoinColumn(name = "COURSE_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false) })
    private Set<Student> students;
}

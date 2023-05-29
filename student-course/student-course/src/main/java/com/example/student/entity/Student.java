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
public class Student implements Serializable {
    private static final long serialVersionUID = 1013479834262222490L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Schema(
            description = " Student Name"
    )
    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Schema(
            description = "Mobile Number"
    )
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Schema(
            description = "Address"
    )
    @Column(name = "ADDRESS")
    private String address;

    @Schema(
            description = " Student Courses"
    )
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students", cascade=CascadeType.ALL)
    private Set<Course> courses;
}

package com.example.student.controller;

import com.example.student.entity.Course;
import com.example.student.entity.Student;
import com.example.student.service.CourseService;
import com.example.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@Tag(name="CRUD REST API for Student Resource",
        description = "CRUD REST APIs - Create  Student, Update Student, Get Student, Get All Student, Delete Student"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/students")
public class StudentCourseController {
    private StudentService studentService;
    private CourseService courseService;


    //build create Student rest api
    @Operation(
            summary = "Create Student REST API",
            description = "Create Student REST API is used to save student in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/student")
    public String addStudent( @RequestBody Student student) {
        studentService.addStudent(student);
        return "Student with Name:" + student.getStudentName() + " has been Added.";
    }

    //build delete student rest api
    @Operation(
            summary = "Delete Student REST API",
            description = "Delete Student REST API is used to delete a single student from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/student/{studentId}")
    public String removeStudent(Long studentId) {
        studentService.removeStudent(studentId);
        return "Student with Id:" + studentId + " has been removed.";
    }

    //build create course rest api
    @Operation(
            summary = "Create Course REST API",
            description = "Create Course REST API is used to save course in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/course")
    public String addCourse( @RequestBody Course course) {
        courseService.addCourse(course);
        return "Course with Name:" + course.getCourseName() + " has been Added.";
    }


    //build delete course rest api
    @Operation(
            summary = "Delete Course REST API",
            description = "Delete Course REST API is used to delete a single course from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/course/{courseId}")
    public String removeCourse(Long courseId) {
        courseService.removeCourse(courseId);
        return "Course with Id:" + courseId + " has been removed.";
    }

    //build register student rest api
    @Operation(
            summary = "Update Student REST API",
            description = "Update Student REST API is used to register a single student with a course and store it in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/registerStudentsToCourse/{courseId}")
    public String enrollStudentToCourse(@PathVariable Long courseId, @RequestBody Set<Student> students) {
        courseService.registerStudentToCourse(courseId, students);
        return "Students has been successfully Enrolled to Course :: " + courseId;
    }

    //build get all Student Rest Api
    @Operation(
            summary = "Get  All Students REST API",
            description = "Get  All Students REST API is used to get all the students from course name from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    @GetMapping("/studentsByCourseName/{courseName}")
    public Set<Student> getStudentsByCourseName(@PathVariable String courseName) {
        return studentService.getStudentsByCourseName(courseName);
    }
}

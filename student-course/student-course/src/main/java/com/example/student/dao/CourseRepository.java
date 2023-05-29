package com.example.student.dao;

import com.example.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public Optional<Course> findCourseByCourseName(String courseName);
}

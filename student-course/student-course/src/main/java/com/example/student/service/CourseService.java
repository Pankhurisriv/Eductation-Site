package com.example.student.service;

import com.example.student.dao.CourseRepository;
import com.example.student.entity.Course;
import com.example.student.entity.Student;
import com.example.student.exception.StudentCourseIllegalStateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseService {


    private CourseRepository courseRepository;


    public Long addCourse(Course course) {
        course = courseRepository.save(course);
        return course.getCourseId();
    }

    public void removeCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            throw new StudentCourseIllegalStateException("Failed to remove Course. Invalid CourseId :: " + courseId);
        }
        courseRepository.delete(course.get());
    }

    public void registerStudentToCourse(Long courseId, Set<Student> students) {

        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new StudentCourseIllegalStateException("Failed to register Student. Invalid CourseId :: " + courseId);
        }
        Course course = courseOptional.get();
        students.addAll(course.getStudents());
        course.setStudents(students);
        courseRepository.save(course);
    }

    public Optional<Course> getCourseByCourseName(String courseName) {
        return courseRepository.findCourseByCourseName(courseName);
    }
}

package com.example.student.service;


import com.example.student.dao.StudentRepository;
import com.example.student.entity.Course;
import com.example.student.entity.Student;
import com.example.student.exception.StudentCourseIllegalStateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;


@Service
@AllArgsConstructor
public class StudentService {


    private StudentRepository studentRepository;
    private CourseService courseService;


    public Long addStudent(Student student) {
        student = studentRepository.save(student);
        return student.getStudentId();
    }

    public void removeStudent(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            throw new StudentCourseIllegalStateException("Failed to remove Student. Invalid StudentId :: " + studentId);
        }
        studentRepository.delete(student.get());
    }

    public void registerCourse(Long studentId, Set<Course> courses) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new StudentCourseIllegalStateException("Failed to register course. Invalid CourseId :: " + studentId);
        }
        Student student = studentOptional.get();
        courses.addAll(student.getCourses());
        student.setCourses(courses);
        studentRepository.save(student);
    }

    public Set<Student> getStudentsByCourseName(String courseName) {
        Optional<Course> course = courseService.getCourseByCourseName(courseName);
        if (!course.isPresent()) {
            throw new StudentCourseIllegalStateException("Failed to get Students. Invalid courseName :: " + courseName);
        }
        Comparator<Student> studentByName = (Student student1, Student student2) -> student1.getStudentName()
                .compareTo(student2.getStudentName());
        TreeSet<Student> sortedStudents = new TreeSet<>(studentByName);

        Set<Student> students = course.get().getStudents();
        students.forEach(student -> student.setCourses(null));
        sortedStudents.addAll(students);
        return sortedStudents;
    }
}

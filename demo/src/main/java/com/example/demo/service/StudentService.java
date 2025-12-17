package com.example.demo.service;
 
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;


@Service
public class StudentService {
    


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    @Transactional(readOnly=true)
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    
    @Transactional(readOnly=true)
    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    @Transactional
    public Student enrollCourse(Long studentId,Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        // course.getStudents().add(student);
        return student;
    }
    @Transactional
    public Student updaStudent(Long studentId,Student student){
        Student existing = studentRepository.findById(studentId).orElseThrow();
        existing.setName(student.getName());
        existing.setCourses(student.getCourses());
        return studentRepository.save(existing);
    }

    @Transactional
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }


}

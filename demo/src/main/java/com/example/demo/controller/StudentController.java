package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;





@RestController
@RequestMapping("/student")
public class StudentController {

    private final  StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping()
    public ResponseEntity<List<Student>> StudentList(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student s) {
         return new ResponseEntity<>(studentService.addStudent(s),HttpStatus.FOUND);
    }
    

    @PostMapping("/{id}")
    public ResponseEntity<Student> studentById(@PathVariable Long id) {
        
        Optional<Student> studentExist = studentService.getStudentById(id);
        if(studentExist.isPresent()){
            return new ResponseEntity<>(studentExist.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> entrollCourse(@PathVariable Long studentId,@PathVariable Long courseId) {
        Student student = studentService.enrollCourse(studentId, courseId);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

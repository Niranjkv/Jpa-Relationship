package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long courseId){
        return courseRepository.findById(courseId);
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id,Course updatedCourse){
        Course course = courseRepository.findById(id).orElseThrow();
        course.setTitle(updatedCourse.getTitle());
        course.setStudents(updatedCourse.getStudents());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
    }
    
}

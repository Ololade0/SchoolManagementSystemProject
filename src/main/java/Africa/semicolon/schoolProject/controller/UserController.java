package Africa.semicolon.schoolProject.controller;


import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.*;

import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;


import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import Africa.semicolon.schoolProject.exception.*;
import Africa.semicolon.schoolProject.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/school")
    public ResponseEntity<?> registerSchool(@RequestBody RegisterSchoolRequest registerSchoolRequest) {
        try {
            RegisterSchoolResponse registerSchoolResponse = schoolService.registerSchool(registerSchoolRequest);
            return new ResponseEntity<>(registerSchoolResponse, HttpStatus.ACCEPTED);
        } catch (SchoolExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/student")
    public ResponseEntity<?> admitStudent(@RequestBody AdmitStudentRequest admitStudentRequest) {

        try {
            AdmitStudentResponse admitStudentResponse = schoolService.admitStudent(admitStudentRequest);
            return new ResponseEntity<>(admitStudentResponse, HttpStatus.ACCEPTED);
        } catch (StudentExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/course")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        try {
            CreateCourseResponse createCourseResponse = schoolService.createCourse(createCourseRequest);
            return new ResponseEntity<>(createCourseResponse, HttpStatus.CREATED);
        } catch (CourseExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all-student")
    public ResponseEntity<?> getAllStudent() {
        return new ResponseEntity<>(schoolService.getAllStudents(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/a-course")
    public ResponseEntity<?> GetACourse(@RequestBody GetACourseRequest getACourseRequest) {
        try {
            Optional<Course> getACourseResponse = schoolService.getACourse(getACourseRequest);
            return new ResponseEntity<>(getACourseResponse, HttpStatus.ACCEPTED);
        } catch (CourseCanNotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/delete-course")
    public ResponseEntity<?> deleteCourse(@RequestBody DeleteCourseRequest deleteCourseRequest) {
        try {
            String deleteCourseResponse = schoolService.deleteCourse(deleteCourseRequest);
            return new ResponseEntity<>(deleteCourseResponse, HttpStatus.ACCEPTED);
        } catch (CourseCanNotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all-courses")
    public ResponseEntity<?> getAllCourses() {
        return new ResponseEntity<>(schoolService.getAllCourses(), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete-student")
    public ResponseEntity<?> deleteStudent(@RequestBody DeleteStudentRequest deleteStudentRequest) {
        try {
            String deleteStudentResponse = schoolService.deleteStudent(deleteStudentRequest);
            return new ResponseEntity<>(deleteStudentResponse, HttpStatus.ACCEPTED);
        } catch (StudentDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}



package Africa.semicolon.schoolProject.controller;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;

import Africa.semicolon.schoolProject.dto.response.SelectCourseResponse;

import Africa.semicolon.schoolProject.exception.CourseExistException;

import Africa.semicolon.schoolProject.exception.StudentDoesNotExistException;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import Africa.semicolon.schoolProject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<?> AdmitStudent(@RequestBody AdmitStudentRequest admitStudentRequest) {
        try {
            Student admitStudentResponse = studentService.admitstudent(admitStudentRequest);
            return new ResponseEntity<>(admitStudentResponse, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<?> findStudentById(@PathVariable String studentId) {
        try {
            Student student = studentService.findStudentById(studentId);
            return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
        } catch (StudentExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



    @PostMapping("courses/select-course")
    public ResponseEntity<?> selectCourses(@RequestBody SelectCourseRequest selectCourseRequest) {
        try {
            SelectCourseResponse selectCourseResponse = studentService.selectCourseById(selectCourseRequest);
            return new ResponseEntity<>(selectCourseResponse, HttpStatus.ACCEPTED);
        } catch (CourseExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("students/all-student")
    public ResponseEntity<?> findAllStudents() {
        try {
            List<Student> students = studentService.findAllStudent();
            return new ResponseEntity<>(students, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{studentId}/student")
    public ResponseEntity<?> DeleteStudentById(@PathVariable String studentId) {
        try {
            String deleteStudent = studentService.deleteById(studentId);
            return new ResponseEntity<>(deleteStudent, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("students/profile-student")
    public ResponseEntity<?> updateStudentProfile(@RequestBody UpdatedStudentProfileRequest updateStudentProfileRequest) {
        try {
            Student updateStudentProfileResponse = studentService.updateStudentProfile(updateStudentProfileRequest);
            return new ResponseEntity<>(updateStudentProfileResponse, HttpStatus.ACCEPTED);
        } catch (StudentDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("student/delete-allstudent")
    public ResponseEntity<?> deleteAllStudents() {
        try {
            String deletedCourses = studentService.deleteAllCourses();
            return new ResponseEntity<>(deletedCourses, HttpStatus.ACCEPTED);
        } catch (StudentDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    }




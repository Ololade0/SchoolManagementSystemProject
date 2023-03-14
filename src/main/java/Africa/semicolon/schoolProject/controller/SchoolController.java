package Africa.semicolon.schoolProject.controller;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;

import Africa.semicolon.schoolProject.dto.response.*;
import Africa.semicolon.schoolProject.exception.CourseExistException;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import Africa.semicolon.schoolProject.services.SchoolService;
import Africa.semicolon.schoolProject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;


    @PostMapping("school")
    public ResponseEntity<?> registerSchool(@RequestBody RegisterSchoolRequest registerSchoolRequest) {
        try {
            RegisterSchoolResponse registerSchoolResponse = schoolService.registerSchool(registerSchoolRequest);
            return new ResponseEntity<>(registerSchoolResponse, HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> findSchoolById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(schoolService.findSchoolById(id), HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-school")
    public ResponseEntity<?> getAllSchools() {
        return new ResponseEntity<>(schoolService.findAllSchools(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllSchools() {
        return new ResponseEntity<>(schoolService.deleteAllSchools(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSchoolById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(schoolService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateSchoolProfileRequest updateSchoolProfileRequest) {
        try {
            School updateSchoolProfile = schoolService.updateSchoolProfile(updateSchoolProfileRequest);
            return new ResponseEntity<>(updateSchoolProfile, HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?> AdmitStudent(@RequestBody AdmitStudentRequest admitStudentRequest) {
        try {
            AdmitStudentResponse admitStudentResponse = schoolService.admitStudent(admitStudentRequest);
            return new ResponseEntity<>(admitStudentResponse, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("student")
    public ResponseEntity<?> findStudentById(@RequestBody GetStudentRequest studentRequest) {
        try {
            Student student = schoolService.findStudentById(studentRequest);
            return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
        } catch (StudentExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all-student")
    public ResponseEntity<?> findAllStudents(GetAllStudentRequest getAllStudentRequest) {
        try {
            List<Student> students = schoolService.findAllStudents(getAllStudentRequest);
            return new ResponseEntity<>(students, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<?> DeleteStudentById(@RequestBody DeleteStudentRequest deleteStudentRequest) {
        try {
            DeleteStudentResponse deleteStudent = schoolService.deleteStudentById(deleteStudentRequest);
            return new ResponseEntity<>(deleteStudent, HttpStatus.ACCEPTED);
        } catch (StudentExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/profile-student")
    public ResponseEntity<?> updateStudentProfile(@RequestBody UpdatedStudentProfileRequest updateStudentProfileRequest) {
        try {
            UpdateStudentProfileResponse updateStudentProfileResponse = schoolService.updateStudentProfile(updateStudentProfileRequest);
            return new ResponseEntity<>(updateStudentProfileResponse, HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-allstudent")
    public ResponseEntity<?> deleteAllStudents() {
        try {
            String deletedSchools = schoolService.deleteAllStudents();
            return new ResponseEntity<>(deletedSchools, HttpStatus.ACCEPTED);
        } catch (StudentExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/course")
    public ResponseEntity<?> CreateCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        try {
            CreateCourseResponse response = schoolService.createCourse(createCourseRequest);
           return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("course")
    public ResponseEntity<?> findCourseById(@RequestBody  GetACourseRequest getACourseRequest) {
        try {
            Course course = schoolService.findCourseById(getACourseRequest);
            return new ResponseEntity<>(course, HttpStatus.ACCEPTED);
        } catch (CourseExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-courses")
    public ResponseEntity<?> findAllCourses(FindAllCourses findAllCourses) {
        try {
            List<Course> courses = schoolService.findAllCourses(findAllCourses);
            return new ResponseEntity<>(courses, HttpStatus.ACCEPTED);
        } catch (CourseExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/profile-course")
    public ResponseEntity<?> updateCourses(@RequestBody UpdateCourseRequest updateCourseRequest) {
        try {
          UpdateCourseResponse updateCourseProfile = schoolService.updateCourseProfile(updateCourseRequest);
            return new ResponseEntity<>(updateCourseProfile, HttpStatus.ACCEPTED);
        } catch (CourseExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}











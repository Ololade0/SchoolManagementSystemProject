package Africa.semicolon.schoolProject.controller;

import Africa.semicolon.schoolProject.dto.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;
import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import Africa.semicolon.schoolProject.exception.CourseExistException;
import Africa.semicolon.schoolProject.exception.SchoolExistException;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import Africa.semicolon.schoolProject.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/school")
    public ResponseEntity<?> registerSchool(@RequestBody RegisterSchoolRequest registerSchoolRequest){
        try {
        RegisterSchoolResponse registerSchoolResponse = schoolService.registerSchool(registerSchoolRequest);
        return new ResponseEntity<>(registerSchoolResponse, HttpStatus.ACCEPTED);
        }catch (SchoolExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("school/student")
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

}

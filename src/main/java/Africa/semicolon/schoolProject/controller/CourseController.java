package Africa.semicolon.schoolProject.controller;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;
import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import Africa.semicolon.schoolProject.exception.CourseExistException;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseServices courseServices;

    @PostMapping("registercourse")
    public ResponseEntity<?> registerCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        try {
            Course createCourseResponse  = courseServices.registerCourse(createCourseRequest);
            return new ResponseEntity<>(createCourseResponse, HttpStatus.ACCEPTED);
        } catch (CourseExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

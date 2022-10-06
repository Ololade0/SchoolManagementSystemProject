package Africa.semicolon.schoolProject.controller;

import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;

import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;


    @PostMapping("/school")
    public ResponseEntity<?> registerSchool(@RequestBody RegisterSchoolRequest registerSchoolRequest) {
        try {
        School registerSchoolResponse =  schoolService.registerSchool(registerSchoolRequest);
            return new ResponseEntity<>(registerSchoolResponse, HttpStatus.ACCEPTED);
        } catch (SchoolDoesExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



}



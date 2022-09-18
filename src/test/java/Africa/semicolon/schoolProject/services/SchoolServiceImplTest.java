package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SchoolServiceImplTest {
    @Autowired
    private SchoolService schoolService;

    RegisterSchoolResponse response;


    @AfterEach
    void tearDown() {
        schoolService.deleteAll();
    }

    @BeforeEach
    void setUp() {
        RegisterSchoolRequest registerSchoolRequest = new RegisterSchoolRequest();
        registerSchoolRequest.setSchoolName("Semicolon");
        registerSchoolRequest.setSchoolLocation("Sabo Yaba");
        response = schoolService.registerSchool(registerSchoolRequest);

    }


    @Test
    void schoolCanBeRegister() {
        assertEquals(1, schoolService.totalUsers());
    }

    @Test
    void schoolCanAdmitStudent() {
        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setStudentAge("32");
        admitStudentRequest.setGender("Female");

        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);
        assertEquals(1L, schoolService.size());
        System.out.println("" + schoolService.getAllStudents());

    }

    @Test
    void SchoolCanDeleteStudent() {
        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setStudentAge("32");
        admitStudentRequest.setGender("Female");

        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);

        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest();
        var school = schoolService.findSchoolByName("Semicolon");
        var student = schoolService.findStudentByEmail("Ololade@gmail.com");

        deleteStudentRequest.setId(school.getId());
        deleteStudentRequest.setStudentId(student.getId());
        schoolService.deleteStudent(deleteStudentRequest);
        assertEquals(0, schoolService.getAllStudents().size());


    }


    @Test
    void schoolCanGetAStudent() {
        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setStudentAge("32");
        admitStudentRequest.setGender("Female");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);

        assertEquals("Ololade", schoolService.getAllStudents().get(0).getStudentLastName());

    }

    @Test
    void schoolCanCreateCourse() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("python");

        schoolService.createCourse(createCourseRequest);
        assertEquals(1, schoolService.size());


    }

    @Test
    void SchoolCanDeleteCourse() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Java");
        createCourseRequest.setCourseName("python");
        createCourseRequest.setSchoolName("semicolon");
        schoolService.createCourse(createCourseRequest);

        DeleteCourseRequest deleteCourseRequest = new DeleteCourseRequest();
        var school = schoolService.findSchoolByName("Semicolon");

        var course = schoolService.getCourseByName("python");

        deleteCourseRequest.setId(school.getId());
        deleteCourseRequest.setCourseId(course.getId());
        System.out.println("" + schoolService.findSchoolByName("Semicolon"));
        schoolService.deleteCourse(deleteCourseRequest);
        assertEquals(0, schoolService.getAllCourses().size());


    }

    @Test
    void schoolCanGetAllCourses() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseStatus("true");
        createCourseRequest.setCourseName("javascript");

        schoolService.createCourse(createCourseRequest);

        schoolService.getAllCourses();
        assertEquals("javascript", schoolService.getAllCourses().get(0).getCourseName());

    }

    @Test
    void schoolCanGetACourses() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("java");
        var res = schoolService.createCourse(createCourseRequest);

        schoolService.getACourse(res.getCourseId());
        assertEquals("java", schoolService.getAllCourses().get(0).getCourseName());

    }

    @Test
    void schoolCanUpdateCourse() {

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("script");
        var res = schoolService.createCourse(createCourseRequest);

        UpdateCourseRequest updateCourseRequest = new UpdateCourseRequest();
        updateCourseRequest.setSchoolId(response.getSchoolId());
        updateCourseRequest.setCourseId(res.getCourseId());
        updateCourseRequest.setCourseName("ruby");
        schoolService.updateCourse(updateCourseRequest);
        assertEquals("ruby", schoolService.getAllCourses().get(0).getCourseName());

    }
}


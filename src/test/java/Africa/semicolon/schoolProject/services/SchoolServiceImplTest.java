package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.dto.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class SchoolServiceImplTest {
    @Autowired
    private SchoolService schoolService;


    @AfterEach
    void tearDown() {
        schoolService.deleteAll();
    }

    @BeforeEach
    void setUp(){
        RegisterSchoolRequest registerSchoolRequest = new RegisterSchoolRequest();
        registerSchoolRequest.setSchoolName("Semicolon");
        registerSchoolRequest.setSchoolLocation("Sabo Yaba");
        schoolService.registerSchool(registerSchoolRequest);

    }




    @Test
    void schoolCanBeRegister(){
        RegisterSchoolRequest registerSchoolRequest = new RegisterSchoolRequest();
      assertEquals(1, schoolService.totalUsers());

    }

    @Test
    void schoolCanAdmitStudent() {
        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);
        assertEquals(1L, schoolService.size());

    }

    @Test
    void SchoolCanDeleteStudent() {
        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setGender("Female");
        admitStudentRequest.setStudentAge("15");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);


        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest();
        var school = schoolService.findSchoolByName("semicolon");
        var student = schoolService.getStudentById("1324");
        deleteStudentRequest.setFirstName("Ashaks");
        deleteStudentRequest.setSchoolName("Semicolon");
        deleteStudentRequest.setLastName("Ololade");
        deleteStudentRequest.setId("12345");
        deleteStudentRequest.setStudentId("1");

        deleteStudentRequest.setStudentId(deleteStudentRequest.getStudentId());
        schoolService.deleteStudent(deleteStudentRequest);

        assertEquals(0, schoolService.size());



    }

    @Test
    void schoolCanCreateCourse() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Java");
        createCourseRequest.setCourseName("python");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);
        assertEquals(1, schoolService.size());

    }

    @Test
    void SchoolCanDeleteCourse() {

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Java");
        createCourseRequest.setCourseName("python");
        createCourseRequest.setSchoolName("semicolon");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);


        DeleteCourseRequest deleteCourseRequest = new DeleteCourseRequest();
        School school = new School();
        var course = schoolService.getCourseByName("java");
        deleteCourseRequest.setId(school.getId());
        deleteCourseRequest.setCourseId(course.getCourseId());
        schoolService.deleteCourse(deleteCourseRequest);

        assertEquals(0, schoolService.getAllCourses().size());


    }

    @Test
    void schoolCanGetAllCourses() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseStatus("true");
        createCourseRequest.setCourseName("javascript");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);

        schoolService.getAllCourses();
        assertEquals("javascript", schoolService.getAllCourses().get(0).getCourseName());

    }

    @Test
    void schoolCanGetACourses() {


        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("java");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);

        GetACourseRequest getACourseRequest = new GetACourseRequest();
        getACourseRequest.setSchoolName("semicolon");
        getACourseRequest.setCourseId("101");
        getACourseRequest.setCourseName(createCourseRequest.getCourseName());
        schoolService.getACourses(getACourseRequest);
        assertEquals("java", schoolService.getAllCourses().get(0).getCourseName());


    }
}


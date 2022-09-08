package Africa.semicolon.schoolProject.services;



import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
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
        admitStudentRequest.setStudentAge("32");
        admitStudentRequest.setGender("Female");
        admitStudentRequest.setStudentId("1011");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);
        assertEquals(1L, schoolService.size());
        System.out.println(""+ schoolService.getAllStudents());

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
        admitStudentRequest.setStudentId("1011");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);

        schoolService.getAStudent("1011");
        assertEquals("Ololade", schoolService.getAllStudents().get(0).getStudentLastName());

    }

    @Test
    void schoolCanCreateCourse() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
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
        var school = schoolService.findSchoolByName("Semicolon");

        var course = schoolService.getCourseByName("python");

        deleteCourseRequest.setId(school.getId());
        deleteCourseRequest.setCourseId(course.getCourseId());
        System.out.println("" + schoolService.findSchoolByName("Semicolon"));
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
        schoolService.getACourse("101");
        assertEquals("java", schoolService.getAllCourses().get(0).getCourseName());

    }
    @Test
    void schoolCanUpdateCourse(){
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("java");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);

        schoolService.findSchoolByName("Semicolon");

        UpdateCourseRequest updateCourseRequest = new UpdateCourseRequest();
        var school = schoolService.findSchoolByName("Semicolon");
        var course1 = schoolService.findCourseById(school.getId());
        var course = schoolService.getCourseByName("java");
        updateCourseRequest.setCourseName("python");
        schoolService.updateCourse(updateCourseRequest);
        assertEquals("" , schoolService.getAllCourses().get(0).getCourseName());








    }
}


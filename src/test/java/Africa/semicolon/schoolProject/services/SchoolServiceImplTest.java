package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;

import Africa.semicolon.schoolProject.dto.request.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class SchoolServiceImplTest {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseRepository courseRepository;





    @AfterEach
    void setUp() {
        schoolService.deleteAll();
    }



    @Test
    void schoolCanBeCreatedTest() {
        School school = new School();
        school.setSchoolName("semicolon");
        assertNotNull(school.getSchoolName());
    }

    @Test
    void schoolCanAdmitStudent() {
        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);
        assertEquals("semicolon", schoolService.findAll().get(0).getSchoolName());
        assertEquals("semicolon",schoolService.findSchoolByName("semicolon").getSchoolName());
        assertEquals("Yaba", schoolService.findSchoolByName("semicolon").getSchoolLocation());

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
        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);


        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
        admitStudentRequest.setStudentFirstName("Ashaks");
        admitStudentRequest.setStudentLastName("Ololade");
        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
        admitStudentRequest.setGender("Female");
        admitStudentRequest.setStudentAge("15");
        admitStudentRequest.setSchoolName("Semicolon");
        schoolService.admitStudent(admitStudentRequest);


        var school = schoolService.findSchoolByName("semicolon");

        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest();
        deleteStudentRequest.setFirstName("Ashaks");
        deleteStudentRequest.setSchoolName("Semicolon");
        deleteStudentRequest.setLastName("Ololade");
        deleteStudentRequest.setId(school.getId());
        var stdId = studentService.getAllStudents().get(0).getId();
        System.out.println("st id "+ stdId);
        deleteStudentRequest.setStudentId(stdId);

        schoolService.deleteStudent(deleteStudentRequest);

        assertEquals(0, schoolService.size());




    }

    @Test
    void schoolCanCreateCourse() {
        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);


        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Java");
        createCourseRequest.setCourseName("python");
        createCourseRequest.setCourseId("101");
        schoolService.createCourse(createCourseRequest);
        assertEquals(1, schoolService.size());


    }

    @Test
    void SchoolCanDeleteCourse() {
        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);



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
        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);


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

        School newSchool = new School();
        newSchool.setSchoolName("semicolon");
        newSchool.setSchoolLocation("Yaba");
        schoolService.save(newSchool);

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


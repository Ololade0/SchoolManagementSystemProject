package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.dto.model.School;
import Africa.semicolon.schoolProject.data.dto.repository.CourseRepository;
import Africa.semicolon.schoolProject.data.dto.repository.SchoolRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

//    @Test
//    void schoolCanAdmitStudent() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//
//        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
//        admitStudentRequest.setStudentFirstName("Ashaks");
//        admitStudentRequest.setStudentLastName("Ololade");
//        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
//        admitStudentRequest.setSchoolName("Semicolon");
//        schoolService.admitStudent(admitStudentRequest);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals(1, schoolRepository.count());
//    }
//
//    @Test
//    void SchoolCanDeleteStudent() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//        assertNotNull(schoolRepository);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals("Yaba", schoolRepository.findAll().get(0).getSchoolLocation());
//
//        AdmitStudentRequest admitStudentRequest = new AdmitStudentRequest();
//        admitStudentRequest.setStudentFirstName("Ashaks");
//        admitStudentRequest.setStudentLastName("Ololade");
//        admitStudentRequest.setEmailAddress("Ololade@gmail.com");
//        admitStudentRequest.setGender("Female");
//        admitStudentRequest.setStudentAge("15");
//        admitStudentRequest.setSchoolName("Semicolon");
//        schoolService.admitStudent(admitStudentRequest);
//        assertEquals(1L, schoolRepository.count());
//
//        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest();
//        deleteStudentRequest.setFirstName("Ashaks");
//        deleteStudentRequest.setSchoolName("Semicolon");
//        deleteStudentRequest.setLastName("Ololade");
//        deleteStudentRequest.setStudentId(studentService.getAllStudents().get(0).getId());
//        schoolService.deleteStudent(deleteStudentRequest);
//        assertEquals(1L, schoolRepository.count());
//    }
//
//    @Test
//    void schoolCanCreateCourse() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//        assertNotNull(schoolRepository);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals("Yaba", schoolRepository.findAll().get(0).getSchoolLocation());
//
//        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
//        createCourseRequest.setCourseName("Java");
//        createCourseRequest.setCourseName("python");
//        createCourseRequest.setCourseId("101");
//        schoolService.createCourse(createCourseRequest);
//        assertEquals(1, schoolRepository.count());
//        assertEquals(1, courseRepository.count());
//    }
//
//    @Test
//    void SchoolCanDeleteCourse() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//        assertNotNull(schoolRepository);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals("semicolon", schoolRepository.findAll().get(0).getSchoolName());
//
//        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
//        createCourseRequest.setCourseName("Java");
//        createCourseRequest.setCourseName("python");
//        createCourseRequest.setSchoolName("semicolon");
//        createCourseRequest.setCourseId("101");
//        schoolService.createCourse(createCourseRequest);
//        assertEquals(1, schoolRepository.count());
//        assertEquals(1, courseRepository.count());
//        var school = schoolRepository.findSchoolBySchoolName("semicolon");
//        var courseToBeDeleted = courseRepository.findById("101").get();
//
//        DeleteCourseRequest deleteCourseRequest = new DeleteCourseRequest();
//
//        deleteCourseRequest.setSchoolName(school.getSchoolName());
//        deleteCourseRequest.setCourseName(courseToBeDeleted.getCourseName());
//        deleteCourseRequest.setCourseId(courseToBeDeleted.getCourseId());
////        deleteCourseRequest.setCourseStatus("Activated");
//        schoolService.deleteCourse(deleteCourseRequest);
//        //  assertEquals(0, courseRepository.count());
//        //  assertEquals(0, schoolRepository.count());
//
//    }
//
//    @Test
//    void schoolCanGetAllCourses() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//        assertNotNull(schoolRepository);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals("Yaba", schoolRepository.findAll().get(0).getSchoolLocation());
//
//        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
//        createCourseRequest.setCourseName("Java");
//        createCourseRequest.setCourseName("python");
//        createCourseRequest.setCourseName("javascript");
//        createCourseRequest.setCourseId("101");
//        schoolService.createCourse(createCourseRequest);
//        assertEquals(1, schoolRepository.count());
//        assertEquals(1, courseRepository.count());
//
//        schoolService.getAllCourses();
//        assertEquals("javascript", schoolService.getAllCourses().get(0).getCourseName());
//      //  assertEquals("", schoolRepository.count());
//        assertEquals("", courseRepository.findAll().get(0).getCourseName());
//
//    }
//
//    @Test
//    void schoolCanGetACourses() {
//        School newSchool = new School();
//        newSchool.setSchoolName("semicolon");
//        newSchool.setSchoolLocation("Yaba");
//        schoolRepository.save(newSchool);
//        assertNotNull(schoolRepository);
//        assertEquals(1L, schoolRepository.count());
//        assertEquals("Yaba", schoolRepository.findAll().get(0).getSchoolLocation());
//
//        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
//        createCourseRequest.setCourseName("Java");
//        createCourseRequest.setCourseName("python");
//        createCourseRequest.setCourseName("javascript");
//        createCourseRequest.setCourseId("101");
//        schoolService.createCourse(createCourseRequest);
//        assertEquals(1, schoolRepository.count());
//        assertEquals(1, courseRepository.count());
//
//       Course course = new Course();
//        course.setCourseName("Java");
//       course.setCourseId("101");
//       schoolService.findACourse("java");
//       assertEquals("", schoolService.findACourse("java").getCourseName());


    }


package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;

import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
public class SchoolServiceImplTest {
    @Autowired
    private SchoolService schoolService;
    RegisterSchoolResponse savedSchool;
    AdmitStudentResponse savedStudent;
    CreateCourseResponse savedCourse;

    @AfterEach
    void tearDown() {

        schoolService.deleteAllSchools();
        schoolService.deleteAllStudents();
        schoolService.deleteAllCourses();
    }

    @BeforeEach
    void setUp() {
        RegisterSchoolRequest registerSchoolRequest = RegisterSchoolRequest
                .builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
       savedSchool =  schoolService.registerSchool(registerSchoolRequest);

        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest
                .builder()
                .studentFirstName("Eunice")
                .studentLastName("Demilade")
                .emailAddress("DemmyOlyns@gmail.com")
                .studentAge("30")
                .studentGender("Female")
                .schoolId(savedSchool.getId())
                .build();
       savedStudent = schoolService.admitStudent(admitStudentRequest);

       CreateCourseRequest createCourseRequest = CreateCourseRequest
                .builder()
                .courseName("Java")
                .courseCode("101")
                .courseStatus("activated")
                .build();
      savedCourse =   schoolService.createCourse(createCourseRequest);

    }
    @Test
    public void testThatCanBeCreated(){
        School newSChool = School.builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
        assertEquals("Semicolon", newSChool.getSchoolName());
    }

    @Test
    public void testThatSchoolCanBeRegister(){

        assertEquals(1, schoolService.TotalNumbersOfSchool());
    }
    @Test
    public void testThatSchoolCanBeFindById(){
       School foundSchool =  schoolService.findSchoolById(savedSchool.getId());
        assertThat(foundSchool.getId()).isEqualTo(savedSchool.getId());
        assertThat(foundSchool.getId()).isGreaterThan("50");
        assertThat(foundSchool).isNotNull();
    }
    @Test
    public void findAllSchools(){
        schoolService.findAllSchools();
        assertEquals("Semicolon", schoolService.findAllSchools().get(0).getSchoolName());
    }
    @Test
    public void deleteAllSchools(){
        schoolService.deleteAllSchools();
        assertEquals(0, schoolService.TotalNumbersOfSchool());

    }
    @Test
    public void schoolCanBeDeletedById(){
        RegisterSchoolRequest registerSchoolRequest = RegisterSchoolRequest
                .builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
        savedSchool =  schoolService.registerSchool(registerSchoolRequest);
        schoolService.deleteById(savedSchool.getId());
        assertEquals(1, schoolService.TotalNumbersOfSchool());
    }

    @Test
    public void schoolProfileCanBeUpdated(){
        UpdateSchoolProfileRequest updateSchoolProfileRequest = UpdateSchoolProfileRequest.builder()
                .schoolLocation("Lekki")
                .schoolName("Unilag")
                .build();
        updateSchoolProfileRequest.setSchoolId(savedSchool.getId());
        schoolService.updateSchoolProfile(updateSchoolProfileRequest);
        assertEquals("Unilag", schoolService.findAllSchools().get(0).getSchoolName());

    }

    @Test
    public void testThatSchoolCanAdmitStudent(){
        assertEquals( 1, schoolService.totalNumberOfStudents());

    }
    @Test
    public void testThatSchoolCanFindStudentById(){
        GetStudentRequest getStudentRequest = new GetStudentRequest();
        getStudentRequest.setStudentId(savedStudent.getStudentId());
        getStudentRequest.setSchoolId(savedSchool.getId());
    Student foundStudent = schoolService.findStudentById(getStudentRequest);
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getStudentId());
        assertThat(foundStudent).isNotNull();

    }

    @Test
    public void testThatSchoolCanFindAllStudents(){
        GetAllStudentRequest getAllStudentRequest = new GetAllStudentRequest();
        getAllStudentRequest.setSchoolId(savedSchool.getId());
        schoolService.findAllStudents(getAllStudentRequest);
        assertEquals("Eunice", schoolService.findAllStudents(getAllStudentRequest).get(0).getStudentFirstName());
        assertEquals(1, schoolService.totalNumberOfStudents());
    }


    @Test
    public void testThatSchoolCanDeleteStudentById(){
        DeleteStudentRequest deleteStudent = new DeleteStudentRequest();
        deleteStudent.setSchoolId(savedSchool.getId());
        deleteStudent.setId(savedStudent.getStudentId());
      DeleteStudentResponse response=  schoolService.deleteStudentById(deleteStudent);
      assertEquals("Student successfully deleted", response.getMessage());
        assertEquals(0, schoolService.totalNumberOfStudents());


    }
    @Test
    public void testThatSchoolCanDeleteAllStudents(){
          schoolService.deleteAllStudents();
        assertEquals(0, schoolService.totalNumberOfStudents());
    }
    @Test
    public void testThatSchoolCanUpdateStudentProfiles(){
        UpdatedStudentProfileRequest updatedStudentProfileRequest = UpdatedStudentProfileRequest
                .builder()
                .studentFirstName("Ololade")
                .studentLastName("Temidayo")
                .studentGender("Male")
                .emailAddress("Olyns@gmail.com")
                .studentAge("80")
                .studentGender("male")
                .build();
        updatedStudentProfileRequest.setSchoolId(savedSchool.getId());
        updatedStudentProfileRequest.setStudentId(savedStudent.getStudentId());
       UpdateStudentProfileResponse response =  schoolService.updateStudentProfile(updatedStudentProfileRequest);
       assertEquals("Student profile successfully updated ", response.getMessage());
       assertThat(response.getId()).isNotNull();
    }
    @Test
    public void testThatSchoolCanCreateCourse(){
        assertEquals(1, schoolService.totalNumberOfCourses());
    }
    @Test
    public void testThatSchoolCanDeleteCourseById(){
        DeleteCourseRequest deleteCourseRequest = new  DeleteCourseRequest();
                deleteCourseRequest.setCourseId(savedCourse.getCourseId());
                deleteCourseRequest.setSchoolId(savedSchool.getId());
       DeleteCourseResponse deletedCourse =  schoolService.deleteCourseById(deleteCourseRequest);
       assertEquals("", deletedCourse.getMessage());
     //   assertEquals(0, schoolService.totalNumberOfCourses());
    }
    @Test
    public void testThatSchoolCanDeleteAllCourse(){
        schoolService.deleteAllCourses();
        assertEquals(0, schoolService.totalNumberOfCourses());

    }
    @Test
    public void testThatSchoolCanFIndCourseById(){
        GetACourseRequest getACourseRequest = new GetACourseRequest();
        getACourseRequest.setSchoolId(savedSchool.getId());
        getACourseRequest.setCourseId(savedCourse.getCourseId());
       Course foundCourse =  schoolService.findCourseById(getACourseRequest);
       assertThat(foundCourse).isNotNull();
       assertThat(foundCourse.getId()).isEqualTo(savedCourse.getCourseId());
        }

    @Test
    public void testThatSchoolCanFIndAllCourses(){
        FindAllCourses findAllCourses = FindAllCourses
                .builder()
                .courseId(savedCourse.getCourseId())
                .schoolId(savedSchool.getId())
                .build();
        schoolService.findAllCourses(findAllCourses);
        assertEquals("Java", schoolService.findAllCourses(findAllCourses).get(0).getCourseName());
        assertEquals("activated", schoolService.findAllCourses(findAllCourses).get(0).getCourseStatus());
        assertEquals("101", schoolService.findAllCourses(findAllCourses).get(0).getCourseCode());

    }
    @Test
    public void testThatSchoolCanUpdateCourses(){


        FindAllCourses findAllCourses = FindAllCourses
                .builder()
                .courseId(savedCourse.getCourseId())
                .schoolId(savedSchool.getId())
                .build();
        schoolService.findAllCourses(findAllCourses);
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .courseName("Javascript")
                .courseCode("106")
                .courseStatus("Disactivated")
               .courseId(savedCourse.getCourseId())
                .schoolId(savedSchool.getId())
                .build();
        schoolService.updateCourseProfile(updateCourseRequest);
       assertEquals("Javascript", schoolService.findAllCourses(findAllCourses).get(0).getCourseName());
       assertEquals("Disactivated", schoolService.findAllCourses(findAllCourses).get(0).getCourseStatus());
        assertEquals("106", schoolService.findAllCourses(findAllCourses).get(0).getCourseCode());
    }











}



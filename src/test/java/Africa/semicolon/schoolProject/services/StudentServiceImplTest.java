package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.RegisterCourseRequest;

import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdatedStudentProfileRequest;

import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;
    Student savedStudent;
    RegisterSchoolResponse registeredCourses;

    @AfterEach
    void tearDown() {
        studentService.deleteAll();
        studentService.deleteAllCourses();

    }

    @BeforeEach
    void setUp() {
        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .emailAddress("Ololade@gmail.com")
                .studentAge("20")
                .studentGender("Female")
                .build();
        savedStudent =   studentService.admitstudent(admitStudentRequest);
        RegisterCourseRequest registerCourseRequest = RegisterCourseRequest
                .builder()
                .courseName("Java")
                .courseCode("101")
                .build();
    registeredCourses = studentService.activatedCourses(registerCourseRequest);


    }


    @Test
    public  void testThatStudentCanBeCreated(){
        Student student = Student.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .email("Ololade@gmail.com")
                .studentAge("20")
                .gender("Female")
                .build();
        assertEquals("Adesuyi" , student.getStudentFirstName());
    }

    @Test
    public  void testThatStudentCanBeAdmited(){
        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .emailAddress("Ololade@gmail.com")
                .studentAge("20")
                .studentGender("Female")
                .build();
        studentService.admitstudent(admitStudentRequest);
        assertEquals(2, studentService.totalNumbersOfStudent());
    }

    @Test
    public  void testThatStudentCanBeFindById(){
       Student foundStudent =  studentService.findStudentById(savedStudent.getId());
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
    }
    @Test
    public  void testThatAllStudentCanBefind(){
      List<Student>  foundStudent =   studentService.findAllStudent();
        assertThat(foundStudent.get(0).getId()).isEqualTo(savedStudent.getId());
        assertEquals("Adesuyi", studentService.findAllStudent().get(0).getStudentFirstName());
    }

    @Test
    public  void testThatAllStudentCanBeDeleted(){
        studentService.deleteAll();
        assertEquals(0, studentService.totalNumbersOfStudent());
    }

    @Test
    public void testThatStudentCanBeDeletedById(){
        studentService.deleteById(savedStudent.getId());
        assertEquals(0, studentService.totalNumbersOfStudent());
    }
    @Test
    public void testThatStudentCanBeUpdated(){
        UpdatedStudentProfileRequest updatedProfileRequest = UpdatedStudentProfileRequest.builder()
                .studentFirstName("Eunice")
                .studentId(savedStudent.getId())
                .studentLastName("Demilade")
                .studentGender("Transgender")
                .emailAddress("DemmyOlyns@gmail.com")
                .studentAge("30")
                .studentGender("Female")
                .build();
        studentService.updateStudentProfile(updatedProfileRequest);
        assertEquals("Eunice", studentService.findAllStudent().get(0).getStudentFirstName());
        assertEquals("Demilade", studentService.findAllStudent().get(0).getStudentLastName());

    }
    @Test
    public void testThatStudentCanARegisterAllCourses(){
        assertEquals(1, studentService.getTotalOfActivatedCourses());
        assertEquals("Java", studentService.findAllCourses().get(0).getCourseName());

    }
    @Test
    public void studentCanSelectCourseById(){
        SelectCourseRequest selectCourseRequest = SelectCourseRequest
                .builder()
                .studentId(savedStudent.getId())
                .courseId(registeredCourses.getCourseId())

                .build();
       var selectedCourse =  studentService.selectCourseById(selectCourseRequest);
        assertThat(selectedCourse.getCourseId()).isEqualTo(registeredCourses.getCourseId());
    }




}
package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
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
                .password("1234")
                .studentAge("20")
                .studentGender("Female")
                .build();
        savedStudent =   studentService.admitstudent(admitStudentRequest);


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
    public void testThatStudentCanLogin() {
        LoginRest loginRest = new LoginRest();
        loginRest.setPassword(savedStudent.getPassword());
        loginRest.setEmail(savedStudent.getEmail());
        var email = studentService.login(loginRest);
        assertEquals(200, email.getCode());
        assertEquals("Login successful", email.getMessage());
    }









}
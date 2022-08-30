package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.StudentRepository;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

//
//    @Test
//    void studentCanBeToAdmitToSchool(){
//        AdmitStudentRequest admitStudentRequest1 = new AdmitStudentRequest();
//        AdmitStudentRequest admitStudentRequest2 = new AdmitStudentRequest();
//        admitStudentRequest2.setStudentAge("13");
//        admitStudentRequest2.setStudentFirstName("Demilade");
//        admitStudentRequest2.setStudentLastName("Eunice");
//        admitStudentRequest2.setEmailAddress("Eunice");
//        studentService.admitStudent(admitStudentRequest2);
//        assertEquals(1, studentRepository.count());
//
//
//    }
}
package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;
import Africa.semicolon.schoolProject.data.repository.StudentRepository;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseReponse;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private CourseServices courseServices;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findStudentById(String studentId) {
        return studentRepository.findStudentById(studentId);

    }

    @Override
    public Student saveNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }


    @Override
    public long size() {
        return studentRepository.count();
    }


    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Override
    public void delete(Student studToDel) {
        studentRepository.delete(studToDel);

    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public SelectCourseReponse selectCourse(SelectCourseRequest selectCourseRequest) {
        Course course = new Course();
        course.setCourseName("");
        course.setCourseCode("");
        return null;


    }

    @Override
    public Student admitStudent(AdmitStudentRequest admitStudentRequest2) {
        Student student = new Student();
        student.setStudentFirstName(admitStudentRequest2.getStudentFirstName());
        student.setStudentLastName(admitStudentRequest2.getStudentLastName());
        School foundSchool = schoolRepository.findSchoolBySchoolName(admitStudentRequest2.getSchoolName());
        if(foundSchool!= null){
            ///studentSErvices.save(student);
            foundSchool.getStudents().add(student);

        }
        return student;

    }

}




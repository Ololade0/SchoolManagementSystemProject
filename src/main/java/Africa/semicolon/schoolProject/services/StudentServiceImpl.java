package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.StudentRepository;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
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
    public Student admitStudent(AdmitStudentRequest admitStudentRequest) {
//        Student foundStudent = studentRepository.findStudentById(admitStudentRequest.getStudentId());
//     if(foundStudent != null){
//             throw new StudentExistException("This student already exist");
//         }
     Student student =  new Student();
     student.setStudentAge("");
     student.setStudentFirstName("");
     student.setStudentLastName("");
     return studentRepository.save(student);

    }
//    @Override
//    public Student admitStudent(AdmitStudentRequest admitStudentRequest) {
//      Student student = new Student();
//      student.setStudentFirstName(admitStudentRequest.getStudentFirstName());
//      student.setStudentAge(admitStudentRequest.getStudentAge());
//      studentRepository.save(student);
//      return student;



    @Override
    public long size() {
        return studentRepository.count();
    }

    @Override
    public Student admitNewStudent(AdmitStudentRequest admitStudentRequest2) {
        Student foundStudent = studentRepository.findStudentById(admitStudentRequest2.getStudentId());
        if(foundStudent != null){
            throw new StudentExistException("This student already exist");
        }
        Student student =  new Student();
        student.setStudentAge(admitStudentRequest2.getStudentAge());
        student.setStudentFirstName(admitStudentRequest2.getStudentFirstName());
        student.setStudentLastName(admitStudentRequest2.getStudentLastName());
        return studentRepository.save(student);
    }
    }




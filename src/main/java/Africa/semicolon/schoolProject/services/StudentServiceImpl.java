package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.StudentRepository;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.DeleteStudentRequest;
import Africa.semicolon.schoolProject.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseServiceImpl courseService;

    @Override
    public Student deleteStudent(DeleteStudentRequest deleteStudentRequest) {
        Student student = studentRepository.findStudentByStudentNumber(deleteStudentRequest.getStudentNumber());

        studentRepository.delete(student);
        return student;
    }


    @Override
    public Student saveNewStudent(AdmitStudentRequest admitStudentRequest) {
        Student newStudent = new Student();
        Mapper.map(admitStudentRequest, newStudent);
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public long size() {
        return studentRepository.count();
    }
    }




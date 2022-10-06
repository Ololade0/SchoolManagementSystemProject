package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Student;

import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;

import Africa.semicolon.schoolProject.dto.request.UpdatedStudentProfileRequest;
import Africa.semicolon.schoolProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student admitstudent(AdmitStudentRequest admitStudentRequest) {
        Student newStudent = Student.builder()
                .studentFirstName(admitStudentRequest.getStudentFirstName())
                .studentLastName(admitStudentRequest.getStudentLastName())
                .studentAge(admitStudentRequest.getStudentAge())
                .email(admitStudentRequest.getEmailAddress())
                .gender(admitStudentRequest.getStudentGender())
                .build();
       return studentRepository.save(newStudent);

    }

    @Override
    public long TotalNUmbersOfStudent() {
        return studentRepository.count();
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();

    }

    @Override
    public Student findStudentById(String id) {
       return studentRepository.findStudentById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        studentRepository.deleteById(id);

    }

    @Override
    public Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest) {
      Student foundStudent =  studentRepository.findStudentById(updatedProfileRequest.getStudentId());
      if(updatedProfileRequest.getStudentFirstName() != null){
          foundStudent.setStudentFirstName(updatedProfileRequest.getStudentFirstName());
      }
        if(updatedProfileRequest.getStudentLastName() != null){
            foundStudent.setStudentLastName(updatedProfileRequest.getStudentLastName());
        }
        if(updatedProfileRequest.getStudentGender() != null){
            foundStudent.setGender(updatedProfileRequest.getStudentGender());
        }
        if(updatedProfileRequest.getStudentAge() != null){
            foundStudent.setStudentAge(updatedProfileRequest.getStudentAge());
        }
      studentRepository.save(foundStudent);
        return foundStudent;
    }
}




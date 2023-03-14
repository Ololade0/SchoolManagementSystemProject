package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;

import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.LoginResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseResponse;
import Africa.semicolon.schoolProject.exception.StudentExistException;
import Africa.semicolon.schoolProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseServices courseServices;


    @Override
    public Student admitstudent(AdmitStudentRequest admitStudentRequest) {
        if (studentRepository.findStudentByEmail(admitStudentRequest.getEmailAddress()).isPresent()) {
            throw new StudentExistException("Student with " + admitStudentRequest.getEmailAddress() + "already exist");
        } else {
            Student newStudent = Student.builder()
                    .studentFirstName(admitStudentRequest.getStudentFirstName())
                    .studentLastName(admitStudentRequest.getStudentLastName())
                    .password(admitStudentRequest.getPassword())
                    .studentAge(admitStudentRequest.getStudentAge())
                    .email(admitStudentRequest.getEmailAddress())
                    .gender(admitStudentRequest.getStudentGender())
                    .build();
            newStudent.setId(newStudent.getId());
            return studentRepository.save(newStudent);

        }

    }

    @Override
    public long totalNumbersOfStudent() {
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
    public String deleteById(String id) {
        studentRepository.deleteById(id);
        return "Student successfully deleted";

    }

    @Override
    public Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest) {
        Student foundStudent = studentRepository.findStudentById(updatedProfileRequest.getStudentId());
        if (updatedProfileRequest.getStudentFirstName() != null) {
            foundStudent.setStudentFirstName(updatedProfileRequest.getStudentFirstName());
        }
        if (updatedProfileRequest.getStudentLastName() != null) {
            foundStudent.setStudentLastName(updatedProfileRequest.getStudentLastName());
        }
        if (updatedProfileRequest.getStudentGender() != null) {
            foundStudent.setGender(updatedProfileRequest.getStudentGender());
        }
        if (updatedProfileRequest.getStudentAge() != null) {
            foundStudent.setStudentAge(updatedProfileRequest.getStudentAge());
        }
        studentRepository.save(foundStudent);
        return foundStudent;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseServices.findAllCourses();
    }





    @Override
    public long getTotalOfRegisteredCourses() {
        
        return courseServices.totalNumberOfCourses();
    }

    @Override
    public String deleteAllCourses() {
        courseServices.deleteAll();
        return "All Courses successfully deleted";

    }

    @Override
    public SelectCourseResponse selectCourseById(SelectCourseRequest selectCourseRequest) {
        Course foundCourse = courseServices.selectCoursesById(selectCourseRequest);
        Student foundStudent = studentRepository.findStudentById(selectCourseRequest.getStudentId());
        if(foundStudent != null) {
            if (foundCourse != null) {
            foundStudent.getCourses().add(foundCourse);
                studentRepository.save(foundStudent);
            }
        }
        return SelectCourseResponse.builder()
                .message("Course Successfully registered")
                .courseId(foundCourse.getId())
                .build();
    }


    @Override
    public SelectCourseResponse selectCourseByName(SelectCourseRequest selectCourseRequest) {
       Course foundCourse = courseServices.selectCoursesByName(selectCourseRequest.getCourseName());
        Student foundStudent = studentRepository.findStudentById(selectCourseRequest.getStudentId());
        if(foundStudent != null){
          foundStudent.getCourses().add(foundCourse);
            studentRepository.save(foundStudent);
        }
        return SelectCourseResponse.builder()
                .message("Course Successfully selected")
                .courseId(foundCourse.getId())
                .build();

   }

    @Override
    public LoginResponse login(LoginRest loginRest) {
        var foundStudent = studentRepository.findStudentByEmail(loginRest.getEmail());
        if(foundStudent.isPresent() && foundStudent.get().getPassword().equals(loginRest.getPassword()));
        return buildSuccessfulLogin(foundStudent.get());


}

    private LoginResponse buildSuccessfulLogin(Student student) {
        return LoginResponse.builder()
                .code(200)
                .message("Login successful")
                .build();
    }


}




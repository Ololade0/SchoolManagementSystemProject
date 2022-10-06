package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;

import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;

import Africa.semicolon.schoolProject.dto.request.RegisterCourseRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdatedStudentProfileRequest;
import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseResponse;
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
        Student newStudent = Student.builder()
                .studentFirstName(admitStudentRequest.getStudentFirstName())
                .studentLastName(admitStudentRequest.getStudentLastName())
                .studentAge(admitStudentRequest.getStudentAge())
                .email(admitStudentRequest.getEmailAddress())
                .gender(admitStudentRequest.getStudentGender())
                .build();
        newStudent.setId(newStudent.getId());
        return studentRepository.save(newStudent);

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
    public void deleteById(String id) {
        studentRepository.deleteById(id);

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
    public RegisterSchoolResponse activatedCourses(RegisterCourseRequest registerCourseRequest) {
              //  Course foundCourse = courseServices.selectCourse(selectCourseRequest);
        Course foundCourse = courseServices.registerCourse(registerCourseRequest);
        Student foundStudent = studentRepository.findStudentById(registerCourseRequest.getStudentId());
        if (foundStudent != null) {
            foundStudent.getCourses().add(foundCourse);
            studentRepository.save(foundStudent);
        }
        return RegisterSchoolResponse
                .builder()
                .message("Course successfully selected")
                .courseId(foundCourse.getId())
                .build();
            }

    @Override
    public long getTotalOfActivatedCourses() {
        return courseServices.totalNumberOfCourses();
    }

    @Override
    public void deleteAllCourses() {
        courseServices.deleteAll();

    }

    @Override
    public SelectCourseResponse selectCourseById(SelectCourseRequest selectCourseRequest) {
        Course foundCourse = courseServices.selectCourse(selectCourseRequest);
        Student foundStudent = studentRepository.findStudentById(selectCourseRequest.getStudentId());
        if(foundStudent != null){
            foundStudent.getCourses().add(foundCourse);
            studentRepository.save(foundStudent);
        }
        return SelectCourseResponse.builder()
                .message("Course Successfully registered")
                .courseId(foundCourse.getId())
                .build();
    }


}




package Africa.semicolon.schoolProject.utils;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.DeleteStudentRequest;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.response.FindCourseResponse;
import Africa.semicolon.schoolProject.dto.response.FindStudentResponse;

public class Mapper {

    public static void map(AdmitStudentRequest admitStudentRequest, Student newStudent) {
        newStudent.setFirstName(admitStudentRequest.getFirstName());
        newStudent.setLastName(admitStudentRequest.getLastName());
        newStudent.setGender(admitStudentRequest.getGender());
        newStudent.setAge(admitStudentRequest.getAge());
        newStudent.setEmail(admitStudentRequest.getEmailAddress());
    }

    public static void map(RegisterSchoolRequest registerSchoolRequest, School newSchool) {
        newSchool.setSchoolName(registerSchoolRequest.getSchoolName());
        newSchool.setSchoolLocation(registerSchoolRequest.getSchoolLocation());
    }

    public static void map(FindStudentResponse findStudentResponse, Student student) {
        findStudentResponse.setFirstName(student.getFirstName());
        findStudentResponse.setLastName(student.getLastName());
        findStudentResponse.setStudentNumber(student.getStudentNumber());
        findStudentResponse.setEmail(student.getEmail());
        findStudentResponse.setAge(student.getAge());
        findStudentResponse.setCoursesOfferedByStudent(student.getCoursesOfferedByStudent());
    }

    public static void map(FindCourseResponse findCourseResponse, Course course) {
        findCourseResponse.setCourseName(course.getCourseName());
        findCourseResponse.setCourseCode(course.getCourseCode());
    }

//    public static void map(DeleteStudentRequest deleteStudentRequest, Student student) {
//        student.setFirstName(deleteStudentRequest.getFirstName());
//        student.setLastName(deleteStudentRequest.getLastName());
//        student.setStudentNumber(deleteStudentRequest.getStudentNumber());
//    }
}

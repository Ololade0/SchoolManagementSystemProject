package Africa.semicolon.schoolProject.data.dto.repository;

import Africa.semicolon.schoolProject.data.dto.model.School;
import Africa.semicolon.schoolProject.data.dto.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findStudentById(String studentId);

    Student findStudentByStudentFirstName(String firstName);
}


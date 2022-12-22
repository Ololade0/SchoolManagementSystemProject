package Africa.semicolon.schoolProject.repository;

import Africa.semicolon.schoolProject.data.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findStudentById(String studentId);
    Optional<Student> findStudentByEmail(String studentEmail);
}

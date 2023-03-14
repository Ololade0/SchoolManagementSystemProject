package Africa.semicolon.schoolProject.repository;

import Africa.semicolon.schoolProject.data.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {
    School findSchoolById(String schoolId);
    Optional<School>findSchoolByEmail(String email);
    Optional<School>findSchoolBySchoolName(String schoolName);
}

package Africa.semicolon.schoolProject.data.repository;


import Africa.semicolon.schoolProject.data.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepository extends MongoRepository<School, String> {

    School findSchoolBySchoolNameIgnoreCase(String name);
}

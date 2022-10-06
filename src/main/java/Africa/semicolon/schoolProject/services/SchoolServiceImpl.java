package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateSchoolProfileRequest;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;


    @Override
    public School registerSchool(RegisterSchoolRequest registerSchoolRequest) {
        School newSchool = School.builder()
                .schoolName(registerSchoolRequest.getSchoolName())
                .schoolLocation(registerSchoolRequest.getSchoolLocation())
                .build();
        return schoolRepository.save(newSchool);
    }

    @Override
    public long TotalNumbersOfSchool() {
        return schoolRepository.count();
    }

    @Override
    public School findSchoolById(String id) {
        return schoolRepository.findById(id).orElseThrow(
                ()-> new SchoolDoesExistException("School Cannot be found")
        );
    }

    @Override
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public void deleteAllSchools() {
        schoolRepository.deleteAll();

    }

    @Override
    public void deleteById(String id) {
        schoolRepository.deleteById(id);

    }

    @Override
    public School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest) {
       School foundSchool =  schoolRepository.findSchoolById(updateSchoolProfileRequest.getSchoolId());
       if(updateSchoolProfileRequest.getSchoolName() != null){
           foundSchool.setSchoolName(updateSchoolProfileRequest.getSchoolName());
       }
        if(updateSchoolProfileRequest.getSchoolLocation() != null){
            foundSchool.setSchoolLocation(updateSchoolProfileRequest.getSchoolLocation());
        }
        schoolRepository.save(foundSchool);

        return foundSchool;
    }
}

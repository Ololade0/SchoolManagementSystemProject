package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateSchoolProfileRequest;

import java.util.List;

public interface SchoolService {


    School registerSchool(RegisterSchoolRequest registerSchoolRequest);

    long TotalNumbersOfSchool();


    School findSchoolById(String id);

    List<School> findAllSchools();

    void deleteAllSchools();

    void deleteById(String id);


    School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest);
}

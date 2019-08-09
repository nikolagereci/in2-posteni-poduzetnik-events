package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceDataSeviceImpl implements ReferenceDataService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public Iterable<City> getCities() {
        return cityRepository.findAll();
    }
}

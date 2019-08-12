package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import hr.in2.postenipoduzetnikevents.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public City getCity(Long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Iterable<City> getCitiesByRegions(List<OrgUnit> regions) {
        return cityRepository.findByOrgUnit_ParentIn(regions);
    }

    @Override
    public Iterable<City> getCitiesByCounties(List<OrgUnit> counties) {
        return cityRepository.findByOrgUnitIn(counties);
    }

    @Override
    public Iterable<City> searchCities(List<OrgUnit> regions, List<OrgUnit> counties, CitySize citySize) {
        return cityRepository.searchCities(regions, counties, citySize);
    }
}

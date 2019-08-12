package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface CityService {
    City getCity(Long id);
    Iterable<City> getAllCities();
    Iterable<City> getCitiesByRegions(List<OrgUnit> regions);
    Iterable<City> getCitiesByCounties(List<OrgUnit> counties);
    Iterable<City> searchCities (List<OrgUnit> regions, List<OrgUnit> counties, CitySize citySize);
}

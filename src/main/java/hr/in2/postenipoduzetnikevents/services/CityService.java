package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface CityService {
    public City getCity (Long id);
    public Iterable<City> getAllCities();
    public Iterable<City> getCitiesByRegions(List<OrgUnit> regions);
    public Iterable<City> getCitiesByCounties(List<OrgUnit> counties);
}

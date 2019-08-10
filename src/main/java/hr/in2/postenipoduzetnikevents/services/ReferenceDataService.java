package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;

public interface ReferenceDataService {
    public Iterable<City> getCities();
    public City getCity (Long id);
}

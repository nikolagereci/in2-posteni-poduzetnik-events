package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface CityRepositoryCustom {
    /**
     * Metoda vraća gradove prema parametrima. Null parametri se ignoriraju
     * @param regions
     * @param counties
     * @param citySizes
     * @return
     */
    Iterable<City> searchCities(List<OrgUnit> regions, List<OrgUnit> counties, List<CitySize> citySizes);
}

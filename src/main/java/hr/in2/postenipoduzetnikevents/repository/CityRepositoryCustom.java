package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepositoryCustom {
    /**
     * Metoda vraća gradove prema parametrima. Null parametri se ignoriraju
     * @param regions
     * @param counties
     * @param citySize
     * @return
     */
    Iterable<City> searchCities(List<OrgUnit> regions, List<OrgUnit> counties, CitySize citySize);
}

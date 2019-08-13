package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import org.springframework.data.repository.CrudRepository;

import hr.in2.postenipoduzetnikevents.model.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long>, CityRepositoryCustom {
    /**
     * Metoda vraća sve gradove iz županija
     * @param counties
     * @return List<City>
     */
    List<City> findByOrgUnitIn(List<OrgUnit> counties);

    /**
     * Metoda vraća sve gradove iz regija
     * @param regions
     * @return List<City>
     */
    List<City> findByOrgUnit_ParentIn(List<OrgUnit> regions);


//    @Query("SELECT c FROM City c WHERE (:regions is null or c.orgUnit.parent in :regions) " +
//            "and (:counties is null or c.orgUnit in :counties)" +
//            "and (:citySize is null or c.citySize = :citySize)")
//    List<City> findByOrgUnit_ParentInAndOrgUnitInAndCitySize(@Param("regions") List<OrgUnit> regions,
//                                                             @Param("counties") List<OrgUnit> counties,
//                                                             @Param("citySize") CitySize citySize);
}

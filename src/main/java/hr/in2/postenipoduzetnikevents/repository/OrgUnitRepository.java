package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.OrgUnitType;
import org.springframework.data.repository.CrudRepository;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface OrgUnitRepository extends CrudRepository<OrgUnit, Long> {
    List<OrgUnit> findByOrgUnitType(OrgUnitType orgUnitType);
    List<OrgUnit> findByParentIn(List<OrgUnit> orgUnits);
}

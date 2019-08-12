package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface OrgUnitService {
    Iterable<OrgUnit> getAllRegions();
    Iterable<OrgUnit> getAllCounties();
    Iterable<OrgUnit> getCountiesByRegions(List<OrgUnit> regions);
}

package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import java.util.List;

public interface OrgUnitService {
    public Iterable<OrgUnit> getAllRegions();
    public Iterable<OrgUnit> getAllCounties();
    public Iterable<OrgUnit> getCountiesByRegions(List<OrgUnit> regions);
}

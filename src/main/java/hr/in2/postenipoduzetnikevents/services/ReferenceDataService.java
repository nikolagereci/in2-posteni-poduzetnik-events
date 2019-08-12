package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.OrgUnitType;

public interface ReferenceDataService {
    OrgUnitType getRegionType();
    OrgUnitType getCountyType();
}

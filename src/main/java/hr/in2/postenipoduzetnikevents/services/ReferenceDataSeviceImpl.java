package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.OrgUnitType;
import hr.in2.postenipoduzetnikevents.repository.CityRepository;
import hr.in2.postenipoduzetnikevents.repository.OrgUnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceDataSeviceImpl implements ReferenceDataService {

    private static final Long REGION_TYPE = 1L;
    private static final Long COUNTY_TYPE = 2L;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OrgUnitTypeRepository orgUnitTypeRepository;

    @Override
    public OrgUnitType getRegionType() {
        return orgUnitTypeRepository.findById(REGION_TYPE).get();
    }

    @Override
    public OrgUnitType getCountyType() {
        return orgUnitTypeRepository.findById(COUNTY_TYPE).get();
    }
}

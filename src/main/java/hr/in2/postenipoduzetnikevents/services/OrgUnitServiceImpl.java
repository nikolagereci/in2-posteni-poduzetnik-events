package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import hr.in2.postenipoduzetnikevents.repository.OrgUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgUnitServiceImpl implements OrgUnitService {

    @Autowired
    OrgUnitRepository orgUnitRepository;

    @Autowired
    ReferenceDataService referenceDataService;

    @Override
    public Iterable<OrgUnit> getAllRegions() {
        return orgUnitRepository.findByOrgUnitType(referenceDataService.getRegionType());
    }

    @Override
    public Iterable<OrgUnit> getAllCounties() {
        return orgUnitRepository.findByOrgUnitType(referenceDataService.getCountyType());
    }

    @Override
    public Iterable<OrgUnit> getCountiesByRegions(List<OrgUnit> regions) {
        return orgUnitRepository.findByParentIn(regions);
    }

}

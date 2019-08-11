package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import hr.in2.postenipoduzetnikevents.model.OrgUnitType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrgUnitRepositoryTest {

    @Autowired
    OrgUnitRepository orgUnitRepository;

    @Autowired
    OrgUnitTypeRepository orgUnitTypeRepository;

    @Test
    public void findByOrgUnitType() {
        //Nađi sve regije
        OrgUnitType regija = orgUnitTypeRepository.findById(1L).get();
        List<OrgUnit> regije = orgUnitRepository.findByOrgUnitType(regija);
        assertEquals(5, regije.size());

        //Nađi sve županije
        OrgUnitType zupanija = orgUnitTypeRepository.findById(2L).get();
        List<OrgUnit> zupanije = orgUnitRepository.findByOrgUnitType(zupanija);
        assertEquals(11, zupanije.size());
    }

    @Test
    public void findByParentIn() {
        List<OrgUnit> criteria = new LinkedList<>();

        //Nađi sve županije u regiji Centralna Hrvatska
        OrgUnit centralna = orgUnitRepository.findById(1L).get();
        criteria.add(centralna);
        List<OrgUnit> zupanijeCentralna = orgUnitRepository.findByParentIn(criteria);
        assertEquals(4, zupanijeCentralna.size());

        //Nađi sve županije u regijama Centralna Hrvatska i Južna Hrvatska obala
        OrgUnit juzna = orgUnitRepository.findById(5L).get();
        criteria.add(juzna);
        List<OrgUnit> zupanijeCentralnaJuzna = orgUnitRepository.findByParentIn(criteria);
        assertEquals(6, zupanijeCentralnaJuzna.size());

    }
}
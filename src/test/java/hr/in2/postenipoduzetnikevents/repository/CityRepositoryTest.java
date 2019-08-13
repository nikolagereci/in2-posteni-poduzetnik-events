package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CitySizeRepository citySizeRepository;

    @Autowired
    OrgUnitRepository orgUnitRepository;

    @Test
    public void findByOrgUnitIn() {
        List<OrgUnit> criteria = new LinkedList<>();

        //Nadji sve gradove iz Splitsko-Dalmatinske
        OrgUnit splitsko = orgUnitRepository.findById(15L).get();
        criteria.add(splitsko);
        List<City> gradoviSplitsko = cityRepository.findByOrgUnitIn(criteria);
        assertEquals(1, gradoviSplitsko.size());

        //Nadji sve gradove iz Splitsko-Dalmatinske i Osjecko-Baranjske
        OrgUnit osjecko = orgUnitRepository.findById(10L).get();
        criteria.add(osjecko);
        List<City> gradoviSplitskoOsjecko = cityRepository.findByOrgUnitIn(criteria);
        assertEquals(2, gradoviSplitskoOsjecko.size());
    }

    @Test
    public void findByOrgUnit_ParentIn(){
        List<OrgUnit> criteria = new LinkedList<>();

        //Vrati sve gradove iz regije Centralna Hrvatska
        OrgUnit centralna = orgUnitRepository.findById(1L).get();
        criteria.add(centralna);
        List<City> gradoviCentralna = cityRepository.findByOrgUnit_ParentIn(criteria);
        assertEquals(4, gradoviCentralna.size());

        //Vrati sve gradove iz regije Centralna Hrvatska i regije Sjeverna Hrvatska obala
        OrgUnit sjevernaObala = orgUnitRepository.findById(4L).get();
        criteria.add(sjevernaObala);
        List<City> gradoviCentralnaSjevernaObala = cityRepository.findByOrgUnit_ParentIn(criteria);
        assertEquals(5, gradoviCentralnaSjevernaObala.size());
    }

    @Test
    public void searchCities(){
        List<OrgUnit> criteria = new LinkedList<>();

        //Nadji sve gradove iz Splitsko-Dalmatinske
        OrgUnit splitsko = orgUnitRepository.findById(15L).get();
        criteria.add(splitsko);
        Iterable<City> gradoviSplitsko = cityRepository.searchCities(null, criteria, null);
        assertEquals(1, IterableUtils.size(gradoviSplitsko));

        //Nadji sve gradove iz Splitsko-Dalmatinske i Osjecko-Baranjske
        OrgUnit osjecko = orgUnitRepository.findById(10L).get();
        criteria.add(osjecko);
        Iterable<City> gradoviSplitskoOsjecko = cityRepository.searchCities(null, criteria, null);
        assertEquals(2, IterableUtils.size(gradoviSplitskoOsjecko));

        criteria.clear();
        //Vrati sve gradove iz regije Centralna Hrvatska
        OrgUnit centralna = orgUnitRepository.findById(1L).get();
        criteria.add(centralna);
        Iterable<City> gradoviCentralna = cityRepository.searchCities(criteria, null, null);
        assertEquals(4, IterableUtils.size(gradoviCentralna));

        //Vrati sve gradove iz regije Centralna Hrvatska i regije Sjeverna Hrvatska obala
        OrgUnit sjevernaObala = orgUnitRepository.findById(4L).get();
        criteria.add(sjevernaObala);
        Iterable<City> gradoviCentralnaSjevernaObala = cityRepository.searchCities(criteria, null, null);
        assertEquals(5, IterableUtils.size(gradoviCentralnaSjevernaObala));

        //Vrati sve velike gradove iz regija Centralna Hrvatska i regije Sjeverna Hrvatska obala
        CitySize veliki = citySizeRepository.findById(3L).get();
        List<CitySize> velicine = new ArrayList<>();
        velicine.add(veliki);
        Iterable<City> velikiGradoviCentralnaSjevernaObala = cityRepository.searchCities(criteria, null, velicine);
        assertEquals(1, IterableUtils.size(velikiGradoviCentralnaSjevernaObala));
    }
}
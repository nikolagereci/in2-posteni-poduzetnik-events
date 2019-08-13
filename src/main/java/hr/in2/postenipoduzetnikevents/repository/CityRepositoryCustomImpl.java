package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepositoryCustomImpl implements CityRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<City> searchCities(List<OrgUnit> regions, List<OrgUnit> counties, List<CitySize> citySizes) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> query = criteriaBuilder.createQuery(City.class);
        Root<City> city = query.from(City.class);

        List<Predicate> regionPredicates = new ArrayList<>();
        if (regions != null && !regions.isEmpty()){
            Path<OrgUnit> regionPath = city.get("orgUnit").get("parent");
            regions.forEach(region -> regionPredicates.add(criteriaBuilder.equal(regionPath, region)));
        }

        List<Predicate> countyPredicates = new ArrayList<>();
        if (counties != null && !counties.isEmpty()){
            Path<OrgUnit> countyPath = city.get("orgUnit");
            counties.forEach(county -> countyPredicates.add(criteriaBuilder.equal(countyPath, county)));
        }
        List<Predicate>  citySizePredicates = new ArrayList<>();
        if(citySizes != null && !citySizes.isEmpty()){
            Path<CitySize> citySizePath = city.get("citySize");
            citySizes.forEach(citySize -> citySizePredicates.add(criteriaBuilder.equal(citySizePath, citySize)));
        }

        Predicate regionPredicatesOr = criteriaBuilder.or(regionPredicates.toArray(new Predicate[regionPredicates.size()]));
        Predicate countyPredicatesOr = criteriaBuilder.or(countyPredicates.toArray(new Predicate[countyPredicates.size()]));
        Predicate citySizePredicatesOr = criteriaBuilder.or(citySizePredicates.toArray(new Predicate[citySizePredicates.size()]));
        List<Predicate> finalPredicateList = new ArrayList<>();
        if (regionPredicates.size() > 0)
            finalPredicateList.add(regionPredicatesOr);
        if (countyPredicates.size() > 0)
            finalPredicateList.add(countyPredicatesOr);
        if (citySizePredicates.size() > 0)
            finalPredicateList.add(citySizePredicatesOr);
        Predicate finalPredicate = criteriaBuilder.and(finalPredicateList.toArray(new Predicate[finalPredicateList.size()]));
        query.select(city).where(finalPredicate);

        return entityManager.createQuery(query).getResultList();
    }
}

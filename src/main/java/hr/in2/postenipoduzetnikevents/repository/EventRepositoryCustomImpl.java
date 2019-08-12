package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.SearchCriteria;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Event> searchEvents(SearchCriteria criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = criteriaBuilder.createQuery(Event.class);
        Root<Event> event = query.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(criteria.getName())){
            Path<String> namePath = event.get("name");
            predicates.add(criteriaBuilder.like(namePath, "%" + criteria.getName() + "%"));
        }
        Path<LocalDateTime> timePathFrom = event.get("timeFrom");
        if (criteria.getStartFrom() != null && criteria.getStartTo() != null){
            predicates.add(criteriaBuilder.between(timePathFrom, criteria.getStartFrom(), criteria.getStartTo()));
        }
        else if (criteria.getStartFrom() != null && criteria.getStartTo() == null)
            predicates.add(criteriaBuilder.greaterThan(timePathFrom, criteria.getStartFrom()));
        else if (criteria.getStartFrom() == null && criteria.getStartTo() != null)
            predicates.add(criteriaBuilder.lessThan(timePathFrom, criteria.getStartTo()));

        Path<LocalDateTime> timePathTo = event.get("timeTo");
        if (criteria.getEndFrom() != null && criteria.getEndTo() != null){
            predicates.add(criteriaBuilder.between(timePathTo, criteria.getEndFrom(), criteria.getEndTo()));
        }
        else if (criteria.getEndFrom() != null && criteria.getEndTo() == null)
            predicates.add(criteriaBuilder.greaterThan(timePathTo, criteria.getEndFrom()));
        else if (criteria.getEndFrom() == null && criteria.getEndTo() != null)
            predicates.add(criteriaBuilder.lessThan(timePathTo, criteria.getEndTo()));

        if (criteria.getFree() != null){
            Path<Boolean> freePath = event.get("free");
            predicates.add(criteriaBuilder.equal(freePath, criteria.getFree()));
        }

        List<Predicate> cityPredicates = new ArrayList<>();
        if (criteria.getCities() != null && !IterableUtils.isEmpty(criteria.getCities())){
            Path<City> cityPath = event.get("city");
            criteria.getCities().forEach(city -> cityPredicates.add(criteriaBuilder.equal(cityPath, city)));
        }
        Predicate generalPredicatesAnd = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        Predicate cityPredicatesOr = criteriaBuilder.or(cityPredicates.toArray(new Predicate[cityPredicates.size()]));
        Predicate finalPredicate;
        if (cityPredicates.size() > 0)
            finalPredicate = criteriaBuilder.and(generalPredicatesAnd, cityPredicatesOr);
        else
            finalPredicate = criteriaBuilder.and(generalPredicatesAnd);

        query.select(event).where(finalPredicate);

        return entityManager.createQuery(query).getResultList();
    }
}

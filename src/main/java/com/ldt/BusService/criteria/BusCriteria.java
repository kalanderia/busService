package com.ldt.BusService.criteria;
import com.ldt.BusService.entity.Bus;
import com.ldt.BusService.model.BusPage;
import com.ldt.BusService.util.BusReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BusCriteria {
    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    BusCriteria (EntityManager entityManager){
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Bus> findAllWithFilter(BusPage busPage){

        CriteriaQuery<Bus>  busCriteriaQuery = criteriaBuilder.createQuery(Bus.class);
        Root<Bus> busRoot=busCriteriaQuery.from(Bus.class);

        Predicate predicate = getPredicate(busPage.getFilters(),busRoot);
        busCriteriaQuery.where(predicate);

        TypedQuery<Bus> typedQuery = entityManager.createQuery(busCriteriaQuery);
        typedQuery.setFirstResult(busPage.getOffSet() * busPage.getPageSize());
        typedQuery.setMaxResults(busPage.getPageSize());
        Pageable pageable = PageRequest.of(busPage.getOffSet(), busPage.getPageSize());

        long recordCount = 0;
        return new PageImpl<>(typedQuery.getResultList(), pageable, recordCount);
    }

    private Predicate getPredicate (Map<String, String> filters,Root<Bus> busRoot){
        List<Predicate> predicateList = new ArrayList<>();

        if(filters!=null){
            if(!Objects.isNull(filters.get("type")) && !filters.get("type").isEmpty()){
                predicateList.add(criteriaBuilder.equal(busRoot.get("type"),filters.get("type")));
            }
            String filterStatus = filters.get("busReservationStatus");
            if(!Objects.isNull(filters.get("busReservationStatus")) && !filters.get("busReservationStatus").isEmpty()) {
                predicateList.add(criteriaBuilder.equal(criteriaBuilder.upper(busRoot.get("busReservationStatus")),BusReservationStatus.valueOf(filterStatus.toUpperCase())));
            }
            if(!Objects.isNull(filters.get("name")) && !filters.get("name").isEmpty())
            {
                predicateList.add(criteriaBuilder.equal(busRoot.get("name"), filters.get("name")));
            }
        }
       return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

}

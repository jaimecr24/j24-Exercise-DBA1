package com.exercise.ej2.infrastructure;


import com.exercise.ej2.domain.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonaRepoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Persona> getData(HashMap<String, Object> conditions)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = this.getQuery(conditions);
        int limit = (conditions.containsKey("limit")) ? (int) conditions.get("limit") : 0;
        int offset = (conditions.containsKey("page")) ? (int) conditions.get("page") * limit : 0;
        if (limit>0)
            return entityManager.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
        else
            return entityManager.createQuery(query).getResultList();
    }

    private CriteriaQuery<Persona> getQuery(HashMap<String, Object> conditions)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field) {
                case "user", "name", "surname" -> predicates.add(cb.like(root.get(field), (String) value));
                case "dateInf" -> predicates.add(cb.greaterThanOrEqualTo(root.get("created_date"), (Date) value));
                case "dateSup" -> predicates.add(cb.lessThanOrEqualTo(root.get("created_date"), (Date) value));
                case "sorted" -> {
                    switch ((String) value) {
                        case "user" -> orderList.add(cb.asc(root.get("user")));
                        case "name" -> orderList.add(cb.asc(root.get("name")));
                    }
                }
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orderList);
        return query;
    }
}

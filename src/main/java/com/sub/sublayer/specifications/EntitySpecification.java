package com.sub.sublayer.specifications;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class EntitySpecification<T> implements Specification<T> {
    private SearchCriteria criteria;

    public EntitySpecification(SpecSearchCriteria specSearchCriteria) {
        criteria= new SearchCriteria(specSearchCriteria.getKey(),specSearchCriteria.getOperation(),specSearchCriteria.getValue());
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return (Predicate) builder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return (Predicate) builder.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return (Predicate) builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return (Predicate) builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
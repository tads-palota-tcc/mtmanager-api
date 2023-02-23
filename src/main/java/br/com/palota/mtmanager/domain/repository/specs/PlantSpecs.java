package br.com.palota.mtmanager.domain.repository.specs;

import br.com.palota.mtmanager.domain.model.Plant;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class PlantSpecs {

    public static Specification<Plant> withRestriction(String restriction) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(restriction)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + restriction.toUpperCase() + "%"));
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + restriction.toUpperCase() + "%"));
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("address").get("city")), "%" + restriction.toUpperCase() + "%"));
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("address").get("state")), restriction.toUpperCase()));
            } else {
                predicates.add(criteriaBuilder.isNotNull(root.get("id")));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));

        };
    }
}

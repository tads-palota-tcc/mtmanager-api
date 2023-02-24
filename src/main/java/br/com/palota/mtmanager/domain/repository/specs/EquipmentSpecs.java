package br.com.palota.mtmanager.domain.repository.specs;

import br.com.palota.mtmanager.domain.model.Equipment;
import br.com.palota.mtmanager.domain.repository.filter.EquipmentFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

public class EquipmentSpecs {

    public static Specification<Equipment> withFilter(EquipmentFilter filter) {
        return (root, query, criteriaBuilder) -> {

            if (Equipment.class.equals(query.getResultType())) {
                root.fetch("area");
            }

            var predicates = new ArrayList<Predicate>();

            if (!ObjectUtils.isEmpty(filter.getTag())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("tag")), "%" + filter.getTag().toUpperCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getDescription())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), "%" + filter.getDescription().toUpperCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getType())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("type")), "%" + filter.getType() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getAreaCode())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("area").get("code")), "%" + filter.getAreaCode().toUpperCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getPlantCode())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("plant").get("code")), "%" + filter.getPlantCode().toUpperCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}

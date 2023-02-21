package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("select p from Plant p where (:restriction is null or :restriction = '') "
            + "or (lower(p.code) like concat('%', lower(:restriction), '%') "
            + "OR lower(p.name) like concat('%', lower(:restriction), '%') "
            + "OR lower(p.address.city) like concat('%', lower(:restriction), '%')) ORDER BY p.code")
    Page<Plant> findByRestriction(@Param("restriction") String restriction, Pageable pageable);
}

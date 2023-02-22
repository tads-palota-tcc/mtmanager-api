package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("select a from Area a where (:restriction is null or :restriction = '') "
            + "or (lower(a.code) like concat('%', lower(:restriction), '%') "
            + "OR lower(a.name) like concat('%', lower(:restriction), '%') "
            + "OR lower(a.plant.code) like concat('%', lower(:restriction), '%')) ORDER BY a.code")
    Page<Area> findByRestriction(@Param("restriction") String restriction, Pageable pageable);
}

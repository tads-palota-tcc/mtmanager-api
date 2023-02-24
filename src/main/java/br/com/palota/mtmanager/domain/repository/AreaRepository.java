package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

    Optional<Area> findFirstByCodeAndPlantId(String code, Long id);

}

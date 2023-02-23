package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlantRepository extends JpaRepository<Plant, Long>, JpaSpecificationExecutor<Plant> {
}

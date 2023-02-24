package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, JpaSpecificationExecutor<Equipment> {

    Optional<Equipment> findFirstByTagAndAreaId(String tag, Long areaId);

}

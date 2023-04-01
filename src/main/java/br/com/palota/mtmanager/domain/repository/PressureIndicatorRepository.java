package br.com.palota.mtmanager.domain.repository;

import br.com.palota.mtmanager.domain.model.PressureIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PressureIndicatorRepository extends JpaRepository<PressureIndicator, Long>, JpaSpecificationExecutor<PressureIndicator> {
}

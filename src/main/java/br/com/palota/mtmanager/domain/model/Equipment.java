package br.com.palota.mtmanager.domain.model;

import br.com.palota.mtmanager.domain.exception.BusinessException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "equipment")
    private Set<PressureSafetyValve> pressureSafetyValves = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "equipment")
    private Set<PressureIndicator> pressureIndicators = new HashSet<>();

    public void addPressureValve(PressureSafetyValve pressureSafetyValve) {
        if (pressureSafetyValve.getEquipment() != null || !pressureSafetyValve.getEquipment().equals(this)) {
            throw new BusinessException("Válvula de segurança pertence a outro equipamento");
        }
        pressureSafetyValve.setEquipment(this);
        this.pressureSafetyValves.add(pressureSafetyValve);
    }

    public void removePressureValve(PressureSafetyValve pressureSafetyValve) {
        if (!this.pressureSafetyValves.contains(pressureSafetyValve)) {
            throw new BusinessException("Válvula de segurança não pertence a este equipamento");
        }
        this.pressureSafetyValves.remove(pressureSafetyValve);
        pressureSafetyValve.setEquipment(null);
    }

    public void addPressureIndicator(PressureIndicator pressureIndicator) {
        if (pressureIndicator.getEquipment() != null || !pressureIndicator.getEquipment().equals(this)) {
            throw new BusinessException("Válvula de segurança pertence a outro equipamento");
        }
        pressureIndicator.setEquipment(this);
        this.pressureIndicators.add(pressureIndicator);
    }

    public void removePressureIndicator(PressureIndicator pressureIndicator) {
        if (!this.pressureIndicators.contains(pressureIndicator)) {
            throw new BusinessException("Válvula de segurança não pertence a este equipamento");
        }
        this.pressureIndicators.remove(pressureIndicator);
        pressureIndicator.setEquipment(null);
    }

}

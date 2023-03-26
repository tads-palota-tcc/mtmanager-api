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
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_equipments")
public class Equipment extends BaseEntity<Long> {

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
    private FluidClass fluidClass;

    private Double hydrostaticTestPressure;

    private Double maxOperationPressure;

    private Double maxPermissibleWorkingPressure;

    private String manufacturer;

    private String model;

    private String serialNumber;

    private Integer yearOfManufacture;

    private String projectCode;

    private Integer projectCodeEditionYear;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    private Double diameter;

    private Double volume;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "equipment")
    private Set<PressureSafetyValve> pressureSafetyValves = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "equipment")
    private Set<PressureIndicator> pressureIndicators = new HashSet<>();

    public void addPressureSafetyValve(PressureSafetyValve pressureSafetyValve) {
        if (pressureSafetyValve.getEquipment() != null || !pressureSafetyValve.getEquipment().equals(this)) {
            throw new BusinessException("Válvula de segurança pertence a outro equipamento");
        }
        pressureSafetyValve.setEquipment(this);
        this.pressureSafetyValves.add(pressureSafetyValve);
    }

    public void removePressureSafetyValve(PressureSafetyValve pressureSafetyValve) {
        if (!this.pressureSafetyValves.contains(pressureSafetyValve)) {
            throw new BusinessException("Válvula de segurança não pertence a este equipamento");
        }
        this.pressureSafetyValves.remove(pressureSafetyValve);
        pressureSafetyValve.setEquipment(null);
    }

    public void addPressureIndicator(PressureIndicator pressureIndicator) {
        if (pressureIndicator.getEquipment() != null || !pressureIndicator.getEquipment().equals(this)) {
            throw new BusinessException("Indicador de pressão pertence a outro equipamento");
        }
        pressureIndicator.setEquipment(this);
        this.pressureIndicators.add(pressureIndicator);
    }

    public void removePressureIndicator(PressureIndicator pressureIndicator) {
        if (!this.pressureIndicators.contains(pressureIndicator)) {
            throw new BusinessException("Indicador de pressão não pertence a este equipamento");
        }
        this.pressureIndicators.remove(pressureIndicator);
        pressureIndicator.setEquipment(null);
    }

    private PotentialRiskGroup getPotentialRiskGroup() {
        var pv = (maxOperationPressure * volume) / 1000;
        if (pv >= 100.0) return PotentialRiskGroup.GROUP_1;
        if (pv < 100.0 && pv >= 30.0) return PotentialRiskGroup.GROUP_2;
        if (pv < 30.0 && pv >= 2.5) return PotentialRiskGroup.GROUP_3;
        if (pv < 2.5 && pv >= 1.0) return PotentialRiskGroup.GROUP_4;
        return PotentialRiskGroup.GROUP_5;
    }

    public Category getCategory() {
        if (!ObjectUtils.isEmpty(category)) return category;
        var potentialRiskGroup = getPotentialRiskGroup();
        switch (fluidClass) {
            case A -> {
                switch (potentialRiskGroup) {
                    case GROUP_1, GROUP_2 -> category = Category.I;
                    case GROUP_3 -> category = Category.II;
                    default -> category = Category.III;
                }
            }
            case B -> {
                switch (potentialRiskGroup) {
                    case GROUP_1 -> category = Category.I;
                    case GROUP_2 -> category = Category.II;
                    case GROUP_3 -> category = Category.III;
                    case GROUP_4, GROUP_5 -> category = Category.IV;
                }
            }
            case C -> {
                switch (potentialRiskGroup) {
                    case GROUP_1 -> category = Category.I;
                    case GROUP_2 -> category = Category.II;
                    case GROUP_3 -> category = Category.III;
                    case GROUP_4 -> category = Category.IV;
                    case GROUP_5 -> category = Category.V;
                }
            }
            default -> {
                switch (potentialRiskGroup) {
                    case GROUP_1 -> category = Category.II;
                    case GROUP_2 -> category = Category.III;
                    case GROUP_3 -> category = Category.IV;
                    case GROUP_4, GROUP_5 -> category = Category.V;
                }
            }
        }
        return category;
    }

    public boolean getIsNr13Equipment() {
        if (diameter < 150.0) return false;
        if (FluidClass.A.equals(fluidClass)) return true;
        return (maxOperationPressure * volume) > 8.0;
    }

}

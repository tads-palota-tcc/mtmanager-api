package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.Category;
import br.com.palota.mtmanager.domain.model.EquipmentType;
import br.com.palota.mtmanager.domain.model.FluidClass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EquipmentCreationDTO {

    @NotBlank
    @Size(min = 1, max = 40)
    private String tag;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @Valid
    private AreaIdDTO area;

    @NotNull
    private FluidClass fluidClass;

    @NotNull
    private Double maxOperationPressure;

    @NotNull
    private Double maxPermissibleWorkingPressure;

    @NotNull
    @PositiveOrZero
    private Double hydrostaticTestPressure;

    private String manufacturer;

    private String model;

    private String serialNumber;

    private Integer yearOfManufacture;

    private String projectCode;

    private Integer projectCodeEditionYear;

    @NotNull
    @Positive
    private Double diameter;

    @NotNull
    @Positive
    private Double volume;

    @NotNull
    private EquipmentType type;

    private Category category;

}

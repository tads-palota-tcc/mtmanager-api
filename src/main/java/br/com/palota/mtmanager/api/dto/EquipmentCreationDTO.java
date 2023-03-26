package br.com.palota.mtmanager.api.dto;

import br.com.palota.mtmanager.domain.model.Category;
import br.com.palota.mtmanager.domain.model.EquipmentType;
import br.com.palota.mtmanager.domain.model.FluidClass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive
    private Double volume;

    @NotNull
    private Double maxOperationPressure;

    @NotNull
    private EquipmentType type;

    @NotNull
    private FluidClass fluidClass;

    private Category category;

    @NotNull
    @Valid
    private AreaIdDTO area;



}

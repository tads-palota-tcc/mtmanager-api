package br.com.palota.mtmanager.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipmentIdDTO {

    @NotNull
    private Long id;

}

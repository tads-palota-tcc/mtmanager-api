package br.com.palota.mtmanager.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AreaDetailsDTO {

    private Long id;

    private String name;

    private String code;

    private PlantSummaryDTO plant;

    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}

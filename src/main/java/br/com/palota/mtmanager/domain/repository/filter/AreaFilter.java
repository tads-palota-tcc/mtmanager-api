package br.com.palota.mtmanager.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaFilter {

    private Long id;
    private String code;
    private String name;
    private String plantCode;
    private String status;

}

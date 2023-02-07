package br.com.palota.mtmanager.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String streetName;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    private String neighborhood;

    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_state")
    private State state;

    private String zipCode;

}

package org.homegrown.boardgamelibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Size(max = 100, message = "Street length exceeds the limit 100")
    private String street;

    @Size(max = 10, message = "Street number length exceeds the limit 10")
    private String number;

    @Size(max = 10, message = "Box length exceeds the limit 10")
    private String box;

    @Size(max = 20, message = "Zipcode length exceeds the limit 20")
    private String zipcode;

    @Size(max = 100, message = "City length exceeds the limit 100")
    private String city;
}

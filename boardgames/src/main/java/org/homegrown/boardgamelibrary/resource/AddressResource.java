package org.homegrown.boardgamelibrary.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class AddressResource extends ResourceSupport {
    private String street;
    private String number;
    private String box;
    private String zipcode;
    private String city;
}

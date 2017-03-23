package org.homegrown.boardgamelibrary.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Account extends ResourceSupport {
    private UUID uuid;
    private String password;
    private RoleResource role;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResource address;
    private String mobile;
    private Boolean active;
    private Boolean isBlocked;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}

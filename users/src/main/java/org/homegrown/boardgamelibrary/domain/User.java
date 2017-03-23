package org.homegrown.boardgamelibrary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "GAMER")
public class User extends Account {

    @NotNull(message = "First name cannot be empty")
    @Size(min = 1, max = 50, message = "Size must be between 1 and 50")
    protected String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 1, max = 65, message = "Size must be between 1 and 65")
    protected String lastName;

    public User() {
        setRole(Role.USER);
    }
}

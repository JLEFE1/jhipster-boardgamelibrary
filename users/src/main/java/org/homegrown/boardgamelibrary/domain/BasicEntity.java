package org.homegrown.boardgamelibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Slf4j
@MappedSuperclass
public abstract class BasicEntity implements Serializable, Identifiable<UUID> {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @NotNull(message = "Username cannot be empty")
    protected String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 6, message = "Password has a minimum length of 6")
    protected String password;

    @Email(message = "Not a well-formed email address")
    @NotNull(message = "Email cannot be empty")
    protected String email;

    @Embedded
    @Valid
    protected Address address;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    protected Role role = Role.USER;

    @NotNull
    protected Boolean active = Boolean.FALSE;

    @JsonIgnore
    protected String activationCode;

    protected LocalDateTime creationDate = LocalDateTime.now();

    protected LocalDateTime modificationDate = LocalDateTime.now();

    protected UUID createdBy;

    @NotNull
    protected Boolean isBlocked = Boolean.FALSE;

    @NotNull(message = "Mobile cannot be empty")
    @Size(min = 10, max = 14)
    protected String mobile;

    @PrePersist
    public void ensureUuidAndInactiveUser() {
        if (getUuid() == null) {
            log.warn(this.getClass().getSimpleName() + "'s UUID wasn't set on time. "
                    + "object: " + toString());
            setUuid(UUID.randomUUID());
            this.active = Boolean.FALSE;
        }
    }

    public void setEmail(String email) {
        String emailToLower = null;
        if (email != null) {
            emailToLower = email.toLowerCase();
        }
        this.email = emailToLower;
    }

    @JsonIgnore
    @Override
    public UUID getId() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getUuid())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof BasicEntity) {
            final BasicEntity other = (BasicEntity) obj;
            return new EqualsBuilder()
                    .append(getUuid(), other.getUuid())
                    .isEquals();
        } else {
            return false;
        }
    }
}

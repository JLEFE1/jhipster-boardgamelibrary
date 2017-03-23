package org.homegrown.boardgamelibrary.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.homegrown.boardgamelibrary.Utils.MobileFormatter.formatMobile;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "role",
        discriminatorType = DiscriminatorType.STRING
)
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Account extends BasicEntity {

    public void setPassword(String password) {
        this.password = password;
        if (Optional.ofNullable(password).isPresent() && password.length() >= 6) {
            this.password = new BCryptPasswordEncoder().encode(password);
        }
    }

    @PreUpdate
    public void prePersist() {
        modificationDate = LocalDateTime.now();
    }

    public void setMobile(String mobile){
        this.mobile = formatMobile(mobile);
    }
}

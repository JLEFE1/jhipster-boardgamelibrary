package org.homegrown.boardgamelibrary.resource;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.homegrown.boardgamelibrary.exception.ErrorResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_EMPTY)
public class FileResource extends ResourceSupport {
    private Integer numberOfSuccessfullyProcessedRecords;
    private Integer numberOfErrors;

    Resources<ErrorResource> errors;
}

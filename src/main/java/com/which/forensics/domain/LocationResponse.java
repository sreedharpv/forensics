package com.which.forensics.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse implements Serializable {
    private static final long serialVersionUID = -12232342345312224L;

    @JsonProperty
    String location;
    @JsonProperty
    String personFound;

}

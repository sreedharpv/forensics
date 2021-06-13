package com.which.forensics.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Directions {
    String directions;
}

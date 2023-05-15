package com.epam.esm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class {@code Tag} is used to represent tag.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Integer id;
    private String name;
}

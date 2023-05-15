package com.epam.esm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Class {@code GiftCertificate} is used to represent gift certificate.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificate {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
}

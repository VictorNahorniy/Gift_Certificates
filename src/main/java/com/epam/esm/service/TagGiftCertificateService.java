package com.epam.esm.service;

import com.epam.esm.dao.SortType;
import com.epam.esm.models.GiftCertificate;


/**
 * Interface {@code TagGiftCertificateService} describes the business logic of the application
 * for working with the table tag_gift_certificate.
 */
public interface TagGiftCertificateService {
    Iterable<GiftCertificate> getCertificatesByTagName(String tagName);
    Iterable<GiftCertificate> getCertificatesByPartOfNameOrDescription(String template);
    Iterable<GiftCertificate> sortCertificatesByDate(SortType sortType);
    Iterable<GiftCertificate> sortCertificatesByName(SortType sortType);
}

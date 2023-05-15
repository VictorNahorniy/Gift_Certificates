package com.epam.esm.dao;


import com.epam.esm.models.GiftCertificate;


/**
 * This interface is used to provide methods for working with gift_certificate table in database.
 * @see com.epam.esm.models.GiftCertificate
 * @see com.epam.esm.dao.impl.GiftCertificateDaoImpl
 * @see com.epam.esm.service.impl.GiftCertificateServiceImpl
 * @see com.epam.esm.controller.GiftCertificateController
 * @see com.epam.esm.service.GiftCertificateService
 * @see com.epam.esm.dao.GiftCertificateDao
 * @since 1.0
 */
public interface TagGiftCertificateDao {
    Iterable<GiftCertificate> findCertificatesByTagName(String tagName);
    Iterable<GiftCertificate> findCertificatesByPartOfNameOrDescription(String template);
    Iterable<GiftCertificate> sortCertificatesByDate(SortType sortType);
    Iterable<GiftCertificate> sortCertificatesByName(SortType sortType);
}

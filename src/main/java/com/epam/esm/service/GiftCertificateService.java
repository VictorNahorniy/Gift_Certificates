package com.epam.esm.service;

import com.epam.esm.models.GiftCertificate;

import java.lang.reflect.InvocationTargetException;

/**
 * Interface {@code GiftCertificateService} describes the business logic of working with the entity GiftCertificate.
 * Exceptions should be thrown when the database is not available, not responding, or an incorrect entity field is
 * passed to the method.
 */
public interface GiftCertificateService {
    void createGiftCertificate(GiftCertificate giftCertificate);
    void deleteGiftCertificate(int id);
    Iterable<GiftCertificate> getAllGiftCertificates();
    GiftCertificate getGiftCertificateById(int id);
    GiftCertificate updateGiftCertificate(GiftCertificate giftCertificate) throws InvocationTargetException, IllegalAccessException;
}

package com.epam.esm.dao;

import com.epam.esm.models.GiftCertificate;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interface for working with GiftCertificate table in database
 * @see com.epam.esm.models.GiftCertificate
 * @see com.epam.esm.dao.impl.GiftCertificateDaoImpl
 * @see com.epam.esm.service.impl.GiftCertificateServiceImpl
 * @see com.epam.esm.controller.GiftCertificateController
 * @see com.epam.esm.service.GiftCertificateService
 * @see com.epam.esm.dao.GiftCertificateDao
 * @since 1.0
 */
public interface GiftCertificateDao {
    Iterable<GiftCertificate> findAll();
    Optional<GiftCertificate> findById(int id);
    Optional<GiftCertificate> findByName(String name);
    void save(GiftCertificate giftCertificate);
    GiftCertificate update(GiftCertificate giftCertificate, List<String> fields) throws InvocationTargetException, IllegalAccessException;
    void delete(Integer id);
    GiftCertificate mapRowToGiftCertificate(ResultSet row, int rowNum) throws SQLException;

}

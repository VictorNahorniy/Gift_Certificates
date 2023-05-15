package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.errorHandling.customException.Ceritficate.CertificateNotFoundException;
import com.epam.esm.errorHandling.customException.Ceritficate.DuplicateCertificateException;
import com.epam.esm.models.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDao;

    @Override
    public void createGiftCertificate(GiftCertificate giftCertificate) {
        Optional<GiftCertificate> existingGiftCertificateOptional = giftCertificateDao.findByName(giftCertificate.getName());
        if (existingGiftCertificateOptional.isPresent()) {
            throw new DuplicateCertificateException("Gift certificate with name "
                    + giftCertificate.getName() + " already exists", 40901);
        }
        giftCertificateDao.save(giftCertificate);
    }

    @Override
    public void deleteGiftCertificate(int id) {
        if (giftCertificateDao.findById(id).isEmpty()) {
            throw new DuplicateCertificateException("Gift certificate with id "
                    + id + " not found", 40401);
        }
        giftCertificateDao.delete(id);
    }


    @Override
    public Iterable<GiftCertificate> getAllGiftCertificates() {
        return giftCertificateDao.findAll();
    }

    @Override
    public GiftCertificate getGiftCertificateById(int id) {
        return giftCertificateDao.findById(id).orElse(null);
    }

    @Override
    public GiftCertificate updateGiftCertificate(GiftCertificate giftCertificate) throws InvocationTargetException, IllegalAccessException {
        if (giftCertificateDao.findById(giftCertificate.getId()).isEmpty()) {
            throw new CertificateNotFoundException("Gift certificate with id "
                    + giftCertificate.getId() + " not found", 40401);
        }
        return giftCertificateDao.update(giftCertificate, getFieldsListToUpdate(giftCertificate));
    }

    public List<String> getFieldsListToUpdate(GiftCertificate giftCertificate) {
        List<String> fieldsList = new ArrayList<>();
        if (giftCertificate.getName() != null) {
            fieldsList.add("name");
        }
        if (giftCertificate.getDescription() != null) {
            fieldsList.add("description");
        }
        if (giftCertificate.getPrice() != null) {
            fieldsList.add("price");
        }
        if (giftCertificate.getDuration() != null) {
            fieldsList.add("duration");
        }
        fieldsList.add("last_update_date");
        return fieldsList;
    }
}

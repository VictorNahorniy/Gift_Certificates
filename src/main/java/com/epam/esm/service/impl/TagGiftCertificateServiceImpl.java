package com.epam.esm.service.impl;

import com.epam.esm.dao.SortType;
import com.epam.esm.dao.TagGiftCertificateDao;
import com.epam.esm.models.GiftCertificate;
import com.epam.esm.service.TagGiftCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagGiftCertificateServiceImpl implements TagGiftCertificateService {
    private final TagGiftCertificateDao tagGiftCertificateDao;

    @Override
    public Iterable<GiftCertificate> getCertificatesByTagName(String tagName) {
        return tagGiftCertificateDao.findCertificatesByTagName(tagName);
    }

    @Override
    public Iterable<GiftCertificate> getCertificatesByPartOfNameOrDescription(String template) {
        return tagGiftCertificateDao.findCertificatesByPartOfNameOrDescription(template);
    }

    @Override
    public Iterable<GiftCertificate> sortCertificatesByDate(SortType sortType) {
        return tagGiftCertificateDao.sortCertificatesByDate(sortType);
    }

    @Override
    public Iterable<GiftCertificate> sortCertificatesByName(SortType sortType) {
        return tagGiftCertificateDao.sortCertificatesByName(sortType);
    }
}

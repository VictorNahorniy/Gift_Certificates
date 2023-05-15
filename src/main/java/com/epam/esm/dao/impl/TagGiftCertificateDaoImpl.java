package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.SortType;
import com.epam.esm.dao.TagGiftCertificateDao;
import com.epam.esm.models.GiftCertificate;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TagGiftCertificateDaoImpl implements TagGiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateDao giftCertificateDao;

    @Override
    public Iterable<GiftCertificate> findCertificatesByTagName(String tagName) {
        return jdbcTemplate.query(
                "select gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                        "from gift_certificate gc " +
                        "join tag_gift_certificate tgc on gc.id = tgc.gift_certificate_id " +
                        "join tag t on tgc.tag_id = t.id " +
                        "where t.name = ?",
                giftCertificateDao::mapRowToGiftCertificate,
                tagName);
    }

    @Override
    public Iterable<GiftCertificate> findCertificatesByPartOfNameOrDescription(String template) {
        return jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date from gift_certificate gc " +
                        "where gc.name like ? " +
                        "or gc.description like ?",
                giftCertificateDao::mapRowToGiftCertificate,
                "%" + template + "%",
                "%" + template + "%");
    }

    @Override
    public Iterable<GiftCertificate> sortCertificatesByDate(SortType sortType) {
        return jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date from gift_certificate " +
                        "order by create_date " + sortType,
                giftCertificateDao::mapRowToGiftCertificate);
    }

    @Override
    public Iterable<GiftCertificate> sortCertificatesByName(SortType sortType) {
        return jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date from gift_certificate " +
                        "order by name " + sortType,
                giftCertificateDao::mapRowToGiftCertificate);
    }
}

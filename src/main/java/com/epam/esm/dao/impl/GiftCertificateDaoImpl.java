package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.models.GiftCertificate;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<GiftCertificate> findAll() {
        return jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date from gift_certificates.gift_certificate",
                this::mapRowToGiftCertificate
        );
    }

    @Override
    public Optional<GiftCertificate> findById(int id) {
        List<GiftCertificate> results = jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date " +
                        "from gift_certificates.gift_certificate " +
                        "where id=?",
                this::mapRowToGiftCertificate,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        List<GiftCertificate> results = jdbcTemplate.query(
                "select id, name, description, price, duration, create_date, last_update_date " +
                        "from gift_certificates.gift_certificate " +
                        "where name=?",
                this::mapRowToGiftCertificate,
                name);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    @Transactional
    public void save(GiftCertificate giftCertificate) {
        jdbcTemplate.update(
                "insert into gift_certificates.gift_certificate(name, description, price, duration, create_date, last_update_date) " +
                        "values (?,?,?,?,?,?)",
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public GiftCertificate update(GiftCertificate giftCertificate, List<String> fields) throws InvocationTargetException, IllegalAccessException {
        StringBuilder sql = new StringBuilder("UPDATE gift_certificates.gift_certificate SET ");
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            sql.append(field).append(" = ?");
            if (i != fields.size() - 1) {
                sql.append(", ");
            }
            if(field.equals("last_update_date")) {
                values.add(new Timestamp(System.currentTimeMillis()));
            } else {
                values.add(Objects.requireNonNull(BeanUtils.getPropertyDescriptor(GiftCertificate.class, field))
                        .getReadMethod().invoke(giftCertificate));
            }
        }
        sql.append(" WHERE id = ?");
        values.add(giftCertificate.getId());
        fields.add("id");
        int[] argTypes = fields.stream()
                .map(field -> Objects.requireNonNull(BeanUtils.getPropertyDescriptor(GiftCertificate.class,
                                convertDateNaming(field)))
                        .getPropertyType())
                .map(this::javaTypeToSqlParameterType)
                .mapToInt(Integer::intValue)
                .toArray();
        int rowsUpdated = jdbcTemplate.update(sql.toString(), values.toArray(), argTypes);
        if (rowsUpdated == 0) {
            throw new RuntimeException("Failed to update GiftCertificate with ID " + giftCertificate.getId());
        }
        return giftCertificate;
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(
                "delete from gift_certificates.gift_certificate where id=?",
                id);
    }

    @Override
    public GiftCertificate mapRowToGiftCertificate(ResultSet row, int rowNum) throws SQLException {
        return new GiftCertificate(
                row.getInt("id"),
                row.getString("name"),
                row.getString("description"),
                row.getDouble("price"),
                row.getInt("duration"),
                row.getTimestamp("create_date"),
                row.getTimestamp("last_update_date")
        );
    }

    private int javaTypeToSqlParameterType(Class<?> clazz) {
        if (String.class == clazz) {
            return Types.VARCHAR;
        } else if (Integer.class == clazz) {
            return Types.INTEGER;
        } else if (Long.class == clazz) {
            return Types.BIGINT;
        } else if (Float.class == clazz) {
            return Types.FLOAT;
        } else if (Double.class == clazz) {
            return Types.DOUBLE;
        } else if (Boolean.class == clazz) {
            return Types.BOOLEAN;
        } else if (Timestamp.class == clazz) {
            return Types.TIMESTAMP;
        } else {
            return Types.OTHER;
        }
    }

    private String convertDateNaming(String field) {
        if (field.equals("create_date")) {
            return "createDate";
        } else if (field.equals("last_update_date")) {
            return "lastUpdateDate";
        } else {
            return field;
        }
    }
}

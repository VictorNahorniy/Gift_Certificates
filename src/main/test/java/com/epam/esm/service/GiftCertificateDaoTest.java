package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.models.GiftCertificate;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GiftCertificateDaoTest {
    private GiftCertificateDaoImpl dao;
    private EmbeddedDatabase db;

    @BeforeEach
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/schema.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        dao = new GiftCertificateDaoImpl(jdbcTemplate);
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testFindById() {
        Optional<GiftCertificate> certificate = dao.findById(1);
        Assertions.assertTrue(certificate.isPresent());
        Assertions.assertEquals(1, certificate.get().getId());
        Assertions.assertEquals("Spa Day", certificate.get().getName());
        Assertions.assertEquals("A relaxing day at the spa", certificate.get().getDescription());
        Assertions.assertEquals(100.0, certificate.get().getPrice());
        Assertions.assertEquals(60, certificate.get().getDuration());
        Assertions.assertEquals(Timestamp.valueOf("2022-05-10 12:00:00"), certificate.get().getCreateDate());
        Assertions.assertEquals(Timestamp.valueOf("2022-05-10 12:00:00"), certificate.get().getLastUpdateDate());
    }

    @Test
    public void findByName() {
        Optional<GiftCertificate> certificate = dao.findByName("Spa Day");
        Assertions.assertTrue(certificate.isPresent());
        Assertions.assertEquals(1, certificate.get().getId());
        Assertions.assertEquals("Spa Day", certificate.get().getName());
        Assertions.assertEquals("A relaxing day at the spa", certificate.get().getDescription());
        Assertions.assertEquals(100.0, certificate.get().getPrice());
        Assertions.assertEquals(60, certificate.get().getDuration());
        Assertions.assertEquals(Timestamp.valueOf("2022-05-10 12:00:00"), certificate.get().getCreateDate());
        Assertions.assertEquals(Timestamp.valueOf("2022-05-10 12:00:00"), certificate.get().getLastUpdateDate());
    }

    @Test
    public void testFindAll() {
        Assertions.assertEquals(10, ((List<GiftCertificate>) dao.findAll()).size());
    }

    @Test
    public void testDelete() {
        dao.delete(1);
        Assertions.assertEquals(9, ((List<GiftCertificate>) dao.findAll()).size());
    }

    @Test
    public void testUpdate() throws InvocationTargetException, IllegalAccessException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Spa Day");
        certificate.setDescription("A relaxing day at the spa");
        certificate.setPrice(100.0);
        certificate.setDuration(60);

        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("description");
        fields.add("price");
        fields.add("duration");
        fields.add("last_update_date");

        dao.update(certificate, fields);
        Optional<GiftCertificate> updatedCertificate = dao.findById(1);
        Assertions.assertTrue(updatedCertificate.isPresent());
        Assertions.assertEquals(1, updatedCertificate.get().getId());
        Assertions.assertEquals("Spa Day", updatedCertificate.get().getName());
        Assertions.assertEquals("A relaxing day at the spa", updatedCertificate.get().getDescription());
        Assertions.assertEquals(100.0, updatedCertificate.get().getPrice());
        Assertions.assertEquals(60, updatedCertificate.get().getDuration());
    }
}

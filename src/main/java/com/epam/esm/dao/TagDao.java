package com.epam.esm.dao;

import com.epam.esm.models.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


/**
 * Interface for working with tags in database
 * @see com.epam.esm.models.Tag
 * @see com.epam.esm.dao.impl.TagDaoImpl
 * @see com.epam.esm.service.impl.TagServiceImpl
 * @see com.epam.esm.controller.TagController
 * @see com.epam.esm.service.TagService
 * @see com.epam.esm.dao.TagDao
 * @since 1.0
 */
public interface TagDao {
    Iterable<Tag> findAll();
    Optional<Tag> findById(int id);
    Optional<Tag> findByName(String name);
    void save(Tag tag);
    void delete(int id);
    Tag mapRowToTag(ResultSet row, int rowNum) throws SQLException;
}

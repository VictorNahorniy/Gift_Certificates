package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.models.Tag;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TagDaoImpl implements TagDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Tag> findAll() {
        return jdbcTemplate.query(
                "select id, name from tag",
                this::mapRowToTag
        );
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> results = jdbcTemplate.query(
                "select id, name from tag where id=?",
                this::mapRowToTag,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> results = jdbcTemplate.query(
                "select id, name from tag where name=?",
                this::mapRowToTag,
                name);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    @Transactional
    public void save(Tag tag) {
        jdbcTemplate.update(
                "insert into tag (name) values (?)",
                tag.getName());
    }

    @Override
    @Transactional
    public void delete(int id) {
        jdbcTemplate.update(
                "delete from tag where id=?",
                id);
    }


    public Tag mapRowToTag(ResultSet row, int rowNum) throws SQLException {
        return new Tag(
                row.getInt("id"),
                row.getString("name")
        );
    }
}

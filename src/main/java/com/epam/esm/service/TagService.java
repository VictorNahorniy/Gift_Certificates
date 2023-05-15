package com.epam.esm.service;

import com.epam.esm.models.Tag;

import java.util.List;

/**
 * Interface {@code TagService} describes abstract behavior for working with tag.
 * Methods of this class provide transactional support for working with the database.
 */
public interface TagService {
    void createTag(Tag tag);
    void deleteTag(int id);
    List<Tag> getAllTags();
    Tag getTagById(int id);
}

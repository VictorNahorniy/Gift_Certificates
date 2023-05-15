package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.errorHandling.customException.Tag.DuplicateTagException;
import com.epam.esm.errorHandling.customException.Tag.TagNotFoundException;
import com.epam.esm.models.Tag;
import com.epam.esm.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;


    @Override
    public void createTag(Tag tag) {
        Optional<Tag> existingTagOptional = tagDao.findByName(tag.getName());
        if(existingTagOptional.isPresent()){
            throw new DuplicateTagException("Tag with name " + tag.getName() + " already exists", 40902);
        }
        tagDao.save(tag);
    }

    @Override
    public void deleteTag(int id) {
        if(tagDao.findById(id).isEmpty()){
            throw new TagNotFoundException("Tag with id " + id + " not found", 40402);
        }
        tagDao.delete(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return (List<Tag>) tagDao.findAll();
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.findById(id).orElse(null);
    }
}

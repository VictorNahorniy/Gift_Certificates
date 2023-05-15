package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.errorHandling.customException.Tag.DuplicateTagException;
import com.epam.esm.errorHandling.customException.Tag.TagNotFoundException;
import com.epam.esm.models.Tag;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    @Mock
    private TagDaoImpl tagDao;
    @InjectMocks
    private TagServiceImpl tagService;

    private Tag newTag;
    private Tag existingTag;

    @BeforeEach
    public void setUp() {
        newTag = new Tag();
        newTag.setName("Test Tag");

        existingTag = new Tag();
        existingTag.setId(1);
        existingTag.setName("Test Tag");
    }

    @Test
    public void testCreateTag() {
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.empty());
        doNothing().when(tagDao).save(newTag);

        // when
        tagService.createTag(newTag);

        // then
        verify(tagDao, times(1)).findByName(any(String.class));
        verify(tagDao, times(1)).save(newTag);
    }

    @Test
    public void testCreateTagWithExistingTag() {
        // when
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.of(newTag));

        Assertions.assertThrows(DuplicateTagException.class, () ->
                tagService.createTag(newTag));
    }

    @Test
    public void testDeleteTag() {
        // when
        when(tagDao.findById(any(Integer.class))).thenReturn(Optional.of(existingTag));
        doNothing().when(tagDao).delete(existingTag.getId());

        // when
        tagService.deleteTag(existingTag.getId());

        // then
        verify(tagDao, times(1)).findById(any(Integer.class));
        verify(tagDao, times(1)).delete(existingTag.getId());
    }

    @Test
    public void testDeleteTagWithNonExistingTag() {
        // when
        when(tagDao.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(TagNotFoundException.class, () ->
                tagService.deleteTag(existingTag.getId()));
    }
}

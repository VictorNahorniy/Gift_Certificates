package com.epam.esm.controller;

import com.epam.esm.errorHandling.customException.Tag.TagNotFoundException;
import com.epam.esm.models.Tag;
import com.epam.esm.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Tag
 * <p>
 * This class is responsible for handling requests related to Tag
 * and sending the response back to the client.
 * The controller is a REST controller that provides the possibility
 * of manipulation with Tag.
 * The controller returns data in JSON format.
 * The controller handles requests from /tags.
 * The controller has methods for:
 * <ul>
 *     <li>getting all Tags</li>
 *     <li>getting Tag by id</li>
 *     <li>adding Tag</li>
 *     <li>deleting Tag</li>
 * </ul>
 * </p>
 */
@RestController
@AllArgsConstructor
public class TagController {
    @Autowired
    private final TagService tagService;

    @GetMapping("/tags")
    public Iterable<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/tags/{id}")
    public Tag getTagById(@PathVariable Integer id) {
        Tag tag = tagService.getTagById(id);
        if (tag == null) {
            throw new TagNotFoundException("Tag with id " + id + " not found", 40402);
        }
        return tag;
    }

    @PostMapping("/tags")
    public void createTag(@RequestBody Tag tag) {
        tagService.createTag(tag);
    }

    @DeleteMapping("/tags/{id}")
    public void deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }
}

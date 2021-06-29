package com.epam.esm.controller;


import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Tags controller class.
 *
 * @author Dzmitry Ahinski
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    /**
     * Init the tags controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Create a new tag.
     *
     * @param tag an object to be created
     * @return created Tag object.
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.insert(tag);
    }

    /**
     * Finds tag by id
     *
     * @param id the id of tag to be found.
     * @return found tag object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Tag findTagById(@PathVariable long id) {
        return tagService.findById(id);
    }

    /**
     * Finds all tags.
     *
     * @return list with found tags.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    /**
     * Deletes tag by id
     *
     * @return Response entity with NO CONTENT status
     */
    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}

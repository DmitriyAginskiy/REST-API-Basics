package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return ResponseEntity object with some information about creating and response status.
     */
    @PostMapping("/new")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        tagService.insert(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tag created successfully!");
    }

    /**
     * Finds tag by id
     *
     * @param id the id of tag to be found.
     * @return found tag object.
     */
    @GetMapping("/{id}")
    public Tag findTagById(@PathVariable long id) {
        return tagService.findById(id);
    }

    /**
     * Finds all tags.
     *
     * @return list with found tags.
     */
    @GetMapping("/all")
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tag deleted successfully!");
    }
}

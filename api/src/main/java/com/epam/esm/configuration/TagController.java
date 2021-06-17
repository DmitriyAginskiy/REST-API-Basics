package com.epam.esm.configuration;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        tagService.insert(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tag created successfully!");
    }

    @GetMapping("/{id}")
    public Tag findTagById(@PathVariable long id) {
        return tagService.findById(id);
    }

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

package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {
    private static Tag tag = new Tag(1, "SomeTag");
    private TagService service;
    @Mock
    private TagDao dao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new TagServiceImpl(dao);
    }

    @Test
    void insert() {
        Mockito.when(dao.findByName("SomeTag")).thenReturn(Optional.empty());
        Mockito.when(dao.insert(tag)).thenReturn(true);
        boolean actual = service.insert(tag);
        assertTrue(actual);
    }

    @Test
    void delete() {
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(tag));
        Mockito.when(dao.delete(1)).thenReturn(true);
        boolean actual = service.delete(1);
        assertTrue(actual);
    }

    @Test
    void findById() {
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(tag));
        Optional<Tag> actual = dao.findById(1);
        assertEquals(tag, actual.get());
    }

    @Test
    void findByName() {
        Mockito.when(dao.findByName("SomeTag")).thenReturn(Optional.of(tag));
        Optional<Tag> actual = dao.findByName("SomeTag");
        assertEquals(tag, actual.get());
    }

    @Test
    void findAll() {
        List<Tag> expected = new ArrayList<>();
        expected.add(tag);
        Mockito.when(dao.findAll()).thenReturn(expected);
        List<Tag> actual = dao.findAll();
        assertEquals(expected, actual);
    }
}
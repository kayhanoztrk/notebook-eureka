package com.notebook.notebookservice.dto.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NoteDtoTest {
    private NoteDto noteDto;

    @Before
    public void setUp(){
        noteDto = new NoteDto();
        noteDto.setId(1L);
        noteDto.setDescription("description");
        noteDto.setTitle("title");
        noteDto.setCreatedAt(new Date());
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(noteDto);
        Assert.assertNotNull(noteDto.getId());
        Assert.assertNotNull(noteDto.getTitle());
        Assert.assertNotNull(noteDto.getDescription());
        Assert.assertNotNull(noteDto.getCreatedAt());
    }
}

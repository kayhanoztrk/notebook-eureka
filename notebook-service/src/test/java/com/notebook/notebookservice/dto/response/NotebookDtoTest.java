package com.notebook.notebookservice.dto.response;

import com.notebook.notebookservice.dto.request.NoteDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookDtoTest {
    private NotebookDto notebookDto;

    @Before
    public void setUp(){
        notebookDto = new NotebookDto();
        notebookDto.setId(1L);
        notebookDto.setNoteDtoList(Arrays.asList(NoteDto.builder()
                .id(1L)
                .title("title")
                .description("description")
                .createdAt(new Date())
                .build()));
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(notebookDto);
        Assert.assertNotNull(notebookDto.getId());
        Assert.assertNotNull(notebookDto.getNoteDtoList());
    }
}

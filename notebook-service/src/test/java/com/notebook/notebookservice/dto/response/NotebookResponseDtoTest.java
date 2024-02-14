package com.notebook.notebookservice.dto.response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookResponseDtoTest {
    private NotebookResponseDto notebookResponseDto;

    @Before
    public void setUp(){
        notebookResponseDto = new NotebookResponseDto();
        notebookResponseDto.setNotebookId(1L);
        notebookResponseDto.setNoteList(Arrays.asList("note1"));
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(notebookResponseDto);
        Assert.assertNotNull(notebookResponseDto.getNotebookId());
        Assert.assertNotNull(notebookResponseDto.getNoteList());
    }
}

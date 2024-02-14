package com.notebook.notebookservice.mapper;

import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.model.Notebook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookDtoMapperTest {
    private NotebookDtoMapper notebookDtoMapper;
    private Notebook notebook;
    private NotebookResponseDto notebookResponseDto;
    @Before
    public void setUp(){
        notebookDtoMapper = new NotebookDtoMapper();
        notebook = new Notebook();
        notebook.setId(1L);
        notebook.setNoteList(Arrays.asList("note1"));

        notebookResponseDto = new NotebookResponseDto();
        notebookResponseDto.setNotebookId(1L);
        notebookResponseDto.setNoteList(Arrays.asList("noteList1"));
    }

    @Test
    public void testConvertEntityToRespDto(){
        NotebookResponseDto response = notebookDtoMapper.convertEntityToRespDto(notebook);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getNotebookId());
        Assert.assertNotNull(response.getNoteList());
    }
}

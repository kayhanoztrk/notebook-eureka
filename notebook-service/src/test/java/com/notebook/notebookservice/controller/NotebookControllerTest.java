package com.notebook.notebookservice.controller;

import com.notebook.notebookservice.dto.request.NoteDto;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.service.NotebookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookControllerTest {
    private NotebookController notebookController;
    private NotebookService notebookService;
    private Environment environment;
    private NotebookDto notebookDto;

    @Before
    public void setUp(){
        notebookService = Mockito.mock(NotebookService.class);
        environment = Mockito.mock(Environment.class);
        notebookController = new NotebookController(notebookService, environment);
        notebookDto = new NotebookDto();
        notebookDto.setId(1L);
        notebookDto.setNoteDtoList(Arrays.asList(NoteDto.builder()
                .id(1L).title("title").description("description").build()));

        when(notebookService.findAllNotesInNotebookById(any(Long.class)))
                .thenReturn(notebookDto);
    }

    @Test
    public void testFindAllNotesInNotebook(){
        ResponseEntity<NotebookDto> notebookDtoResponseEntity =
                notebookController.findAllNotesInNotebook(1L);

        Assert.assertNotNull(notebookDtoResponseEntity);
        Assert.assertNotNull(notebookDtoResponseEntity.getStatusCode());
        Assert.assertNotNull(notebookDtoResponseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, notebookDtoResponseEntity.getStatusCode());
        Assert.assertEquals(notebookDto, notebookDtoResponseEntity.getBody());

        verify(notebookService, times(1)).findAllNotesInNotebookById(1L);

    }
}

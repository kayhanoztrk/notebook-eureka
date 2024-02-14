package com.notebook.notebookservice.controller;

import com.notebook.notebookservice.dto.request.NoteDto;
import com.notebook.notebookservice.dto.request.NotebookCreateRequest;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.service.NotebookService;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

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
    private NotebookResponseDto notebookResponseDto;
    private NotebookCreateRequest notebookCreateRequest;

    @Before
    public void setUp(){
        notebookService = Mockito.mock(NotebookService.class);
        environment = Mockito.mock(Environment.class);
        notebookController = new NotebookController(notebookService, environment);
        notebookDto = new NotebookDto();
        notebookDto.setId(1L);
        notebookDto.setNoteDtoList(Arrays.asList(NoteDto.builder()
                .id(1L).title("title").description("description").build()));

        notebookResponseDto = NotebookResponseDto.builder()
                        .notebookId(1L)
                        .noteList(Arrays.asList("note1"))
                        .build();

        notebookCreateRequest = NotebookCreateRequest.builder()
                        .notebookId(1L)
                        .notes(Arrays.asList("note1"))
                        .build();

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

    @Test
    public void testCreateNotebook(){
        when(notebookService.createNotebook())
                .thenReturn(notebookResponseDto);
        ResponseEntity<NotebookResponseDto> response =
                notebookController.createNotebook();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getStatusCode());
        Assert.assertNotNull(response.getBody());

        verify(notebookService, times(1)).createNotebook();
    }

    @Test
    public void testAddNoteToNotebook(){
        when(notebookService.addNoteToNotebook(any(NotebookCreateRequest.class)))
                .thenReturn(notebookResponseDto);
        ResponseEntity<NotebookResponseDto> response =
                notebookController.addNoteToNotebook(notebookCreateRequest);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(notebookResponseDto, response.getBody());

        verify(notebookService, times(1)).addNoteToNotebook(
                any(NotebookCreateRequest.class));
    }

    @Test
    public void testFindNotebookById(){
        when(notebookService.findNotebookById(any(Long.class)))
                .thenReturn(notebookResponseDto);

        ResponseEntity<NotebookResponseDto> response =
                notebookController.findNotebookById(1L);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assert.assertEquals(notebookResponseDto, response.getBody());

        verify(notebookService, times(1))
                .findNotebookById(any(Long.class));
    }

    @Test
    public void testGetAllNotebookIdInfo(){
        when(notebookService.getAllNotebookIdInfo())
                .thenReturn(Arrays.asList(1L));
        ResponseEntity<List<Long>> response = notebookController.getAllNotebookIdInfo();

        Assert.assertNotNull(response);
    }
}

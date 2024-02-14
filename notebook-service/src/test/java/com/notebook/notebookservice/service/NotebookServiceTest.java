package com.notebook.notebookservice.service;

import com.notebook.notebookservice.client.NoteServiceClient;
import com.notebook.notebookservice.dto.request.NoteDto;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.exception.NotebookNotFoundException;
import com.notebook.notebookservice.mapper.NotebookDtoMapper;
import com.notebook.notebookservice.model.Notebook;
import com.notebook.notebookservice.repository.NotebookRepository;
import com.notebook.notebookservice.service.impl.NotebookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookServiceTest {
    private NotebookService notebookService;
    private NotebookRepository notebookRepository;
    private NotebookDtoMapper notebookDtoMapper;
    private NoteServiceClient noteServiceClient;
    private Notebook notebook;
    private NotebookResponseDto notebookResponseDto;
    private NoteDto noteDto;

    @Before
    public void setUp(){
        notebookRepository = Mockito.mock(NotebookRepository.class);
        notebookDtoMapper = Mockito.mock(NotebookDtoMapper.class);
        noteServiceClient = Mockito.mock(NoteServiceClient.class);
        notebookService = new NotebookServiceImpl(notebookRepository,
                notebookDtoMapper, noteServiceClient);
        notebook = Notebook.builder()
                        .id(1L)
                        .noteList(Arrays.asList("1"))
                        .build();
        notebookResponseDto = NotebookResponseDto.builder()
                        .notebookId(1L)
                        .noteList(Arrays.asList("notebook1"))
                        .build();

        noteDto = NoteDto.builder()
                        .id(1L)
                        .title("title")
                        .description("description")
                        .build();

        when(notebookRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(notebook));
        when(notebookDtoMapper.convertEntityToRespDto(any(Notebook.class)))
                .thenReturn(notebookResponseDto);
    }

    @Test
    public void testFindAllNotesInNotebookById(){
        when(noteServiceClient.findNoteById(any(Long.class)))
                .thenReturn(new ResponseEntity<>(noteDto, HttpStatus.OK));

        NotebookDto notebookDto = notebookService.findAllNotesInNotebookById(1L);
        Assert.assertNotNull(notebookDto);
        Assert.assertNotNull(notebookDto.getNoteDtoList());
        Assert.assertEquals(notebookDto.getNoteDtoList(), Arrays.asList(noteDto));

        verify(noteServiceClient, times(1)).findNoteById(any(Long.class));
    }


    @Test
    public void testCreateNotebook(){
        when(notebookRepository.save(any(Notebook.class)))
                .thenReturn(notebook);
        when(notebookDtoMapper.convertEntityToRespDto(any(Notebook.class)))
                .thenReturn(notebookResponseDto);

        NotebookResponseDto responseDto = notebookService.createNotebook();
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getNotebookId());

        verify(notebookRepository, times(1)).save(any(Notebook.class));
        verify(notebookDtoMapper, times(1)).convertEntityToRespDto(any(Notebook.class));
    }

    @Test
    public void testFindNotebookById(){
       NotebookResponseDto responseDto = notebookService.findNotebookById(1L);
       Assert.assertNotNull(responseDto);
       Assert.assertEquals(responseDto, notebookResponseDto);
    }

    @Test(expected = NotebookNotFoundException.class)
    public void testFindNotebookById_When_NotebookIdNotFound_Expect_Exception(){
        when(notebookRepository.findById(1L))
                .thenThrow(NotebookNotFoundException.class);

        notebookService.findNotebookById(1L);
        verify(notebookRepository, times(1)).findById(1L);
    }


}

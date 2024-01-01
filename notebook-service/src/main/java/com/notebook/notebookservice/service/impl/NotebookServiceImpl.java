package com.notebook.notebookservice.service.impl;

import com.notebook.notebookservice.client.NoteServiceClient;
import com.notebook.notebookservice.dto.request.NoteDto;
import com.notebook.notebookservice.dto.request.NotebookCreateRequest;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.exception.NotebookNotFoundException;
import com.notebook.notebookservice.mapper.NotebookDtoMapper;
import com.notebook.notebookservice.model.Notebook;
import com.notebook.notebookservice.repository.NotebookRepository;
import com.notebook.notebookservice.service.NotebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Service
public class NotebookServiceImpl implements NotebookService {
    private static final Logger logger = LoggerFactory.getLogger(NotebookServiceImpl.class);
    private final NotebookRepository notebookRepository;
    private final NotebookDtoMapper notebookDtoMapper;
    private final NoteServiceClient noteServiceClient;

    public NotebookServiceImpl(NotebookRepository notebookRepository, NotebookDtoMapper notebookDtoMapper, NoteServiceClient noteServiceClient) {
        this.notebookRepository = notebookRepository;
        this.notebookDtoMapper = notebookDtoMapper;
        this.noteServiceClient = noteServiceClient;
    }

    @Override
    public NotebookDto findAllNotesInNotebookById(Long id) {
        Notebook notebook = notebookRepository.findById(id)
                .orElseThrow(() -> new NotebookNotFoundException("notebook not found id info:" + id));
        logger.info("searching notebook by id {}", id);
        NotebookDto notebookDto = new NotebookDto();
        notebookDto.setId(notebook.getId());
        notebookDto.setNoteDtoList(notebook.getNoteList()
                .stream()
                .map(note -> noteServiceClient.findNoteById(Long.valueOf(note)).getBody())
                .collect(Collectors.toList()));
        return notebookDto;
    }

    @Override
    public NotebookResponseDto findNotebookById(Long id) {
        NotebookResponseDto notebookResponseDto = notebookRepository.findById(id)
                .map(notebook -> notebookDtoMapper.convertEntityToRespDto(notebook))
                .orElseThrow(() -> new NotebookNotFoundException(id + " idNotebook not found!"));

        return notebookResponseDto;
    }

    @Override
    public NotebookResponseDto addNoteToNotebook(NotebookCreateRequest notebookCreateRequest) {
        Notebook notebook = notebookRepository.findById(notebookCreateRequest.getNotebookId())
                .orElseThrow(() -> new NotebookNotFoundException("Notebook not found by id :" + notebookCreateRequest.getNotebookId()));

        NoteDto noteDto = noteServiceClient.findNoteById(Long.valueOf(notebookCreateRequest.getNotes().get(0))).getBody();

        notebook.getNoteList().add(noteDto.getId().toString());
        notebook.setNoteList(notebook.getNoteList());
        Notebook createdNotebook = notebookRepository.save(notebook);
        return notebookDtoMapper.convertEntityToRespDto(createdNotebook);
    }

    @Override
    public NotebookResponseDto createNotebook() {
        Notebook createNotebook = notebookRepository.save(new Notebook());
        return notebookDtoMapper.convertEntityToRespDto(createNotebook);
    }

    @Override
    public List<Long> getAllNotebookIdInfo() {
        return notebookRepository.findAll()
                .stream().map(notebook -> notebook.getId())
                .collect(Collectors.toList());
    }
}

package com.notebook.notebookservice.service;

import com.notebook.notebookservice.dto.request.NotebookCreateRequest;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;

import java.util.List;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface NotebookService {
    NotebookDto findAllNotesInNotebookById(Long id);
    NotebookResponseDto findNotebookById(Long id);
    NotebookResponseDto addNoteToNotebook(NotebookCreateRequest notebookCreateRequest);
    NotebookResponseDto createNotebook();
    List<Long> getAllNotebookIdInfo();
}

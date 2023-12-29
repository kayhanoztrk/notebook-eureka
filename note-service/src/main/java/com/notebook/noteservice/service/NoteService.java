package com.notebook.noteservice.service;

import com.notebook.noteservice.dto.request.NoteCreateRequest;
import com.notebook.noteservice.dto.request.NoteUpdateRequest;
import com.notebook.noteservice.dto.response.NoteDto;

import java.util.List;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface NoteService {
    List<NoteDto> findAllNote();
    NoteDto findNoteById(Long id);
    NoteDto findNoteByTitle(String title);
    NoteDto createNote(NoteCreateRequest noteCreateRequest);
    NoteDto updateNote(Long id, NoteUpdateRequest noteUpdateRequest);
    void deleteNoteById(Long id);
}

package com.notebook.noteservice.service.impl;

import com.notebook.noteservice.dto.request.NoteCreateRequest;
import com.notebook.noteservice.dto.request.NoteUpdateRequest;
import com.notebook.noteservice.dto.response.NoteDto;
import com.notebook.noteservice.exception.NoteNotFoundException;
import com.notebook.noteservice.mapper.NoteDtoMapper;
import com.notebook.noteservice.model.Note;
import com.notebook.noteservice.repository.NoteRepository;
import com.notebook.noteservice.service.NoteService;
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
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteDtoMapper noteDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    public NoteServiceImpl(NoteRepository noteRepository, NoteDtoMapper noteDtoMapper) {
        this.noteRepository = noteRepository;
        this.noteDtoMapper = noteDtoMapper;
    }

    @Override
    public List<NoteDto> findAllNote() {
        return noteRepository.findAll()
                .stream()
                .map(note -> noteDtoMapper.convertEntityToDto(note))
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto findNoteById(Long id) {
        return noteRepository.findById(id)
                .map(note -> noteDtoMapper.convertEntityToDto(note))
                .orElseThrow(() -> new NoteNotFoundException(id + " idNote not found!"));
    }

    @Override
    public NoteDto findNoteByTitle(String title) {

        return noteRepository.findNoteByTitle(title)
                .map(note -> noteDtoMapper.convertEntityToDto(note))
                .orElseThrow(() -> new NoteNotFoundException(title + " titleNote not found!"));
    }

    @Override
    public NoteDto createNote(NoteCreateRequest noteCreateRequest) {
        Note note = noteDtoMapper.convertCreateReqToEntity(noteCreateRequest);
        Note createdNote = noteRepository.save(note);
        return noteDtoMapper.convertEntityToDto(createdNote);
    }

    @Override
    public NoteDto updateNote(Long id, NoteUpdateRequest noteUpdateRequest) {
        Note toNote = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id + " idNote not found!"));
        toNote.setTitle(noteUpdateRequest.getTitle());
        toNote.setDescription(noteUpdateRequest.getDescription());
        Note updatedNote = noteRepository.save(toNote);
        return noteDtoMapper.convertEntityToDto(updatedNote);
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}

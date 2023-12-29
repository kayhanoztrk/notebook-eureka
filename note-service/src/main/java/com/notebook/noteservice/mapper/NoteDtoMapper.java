package com.notebook.noteservice.mapper;

import com.notebook.noteservice.dto.request.NoteCreateRequest;
import com.notebook.noteservice.dto.request.NoteUpdateRequest;
import com.notebook.noteservice.dto.response.NoteDto;
import com.notebook.noteservice.model.Note;
import org.springframework.stereotype.Component;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Component
public class NoteDtoMapper {

    public NoteDto convertEntityToDto(Note note) {
        NoteDto noteDto = NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
        return noteDto;
    }

    public Note convertDtoToEntity(NoteDto noteDto) {
        Note note = Note.builder()
                .id(noteDto.getId())
                .title(noteDto.getTitle())
                .description(noteDto.getDescription())
                .createdAt(noteDto.getCreatedAt())
                .updatedAt(noteDto.getUpdatedAt())
                .build();
        return note;
    }

    public Note convertCreateReqToEntity(NoteCreateRequest noteCreateRequest){
        Note note = Note.builder()
                .title(noteCreateRequest.getTitle())
                .description(noteCreateRequest.getDescription())
                .build();
        return note;
    }

    public Note convertUpdateReqToEntity(NoteUpdateRequest noteUpdateRequest){
        Note note = Note.builder()
                .title(noteUpdateRequest.getTitle())
                .description(noteUpdateRequest.getDescription())
                .build();
        return note;
    }

}

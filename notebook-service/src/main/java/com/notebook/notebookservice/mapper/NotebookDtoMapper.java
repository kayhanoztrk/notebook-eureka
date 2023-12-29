package com.notebook.notebookservice.mapper;

import com.notebook.notebookservice.dto.request.NoteDto;
import com.notebook.notebookservice.dto.request.NotebookCreateRequest;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.model.Notebook;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Component
public class NotebookDtoMapper {

    public NotebookResponseDto convertEntityToRespDto(Notebook notebook){
        NotebookResponseDto notebookResponseDto = NotebookResponseDto
                .builder().notebookId(notebook.getId())
                .noteList(notebook.getNoteList())
                .build();
        return notebookResponseDto;
    }

}

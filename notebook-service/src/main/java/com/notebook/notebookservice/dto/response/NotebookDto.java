package com.notebook.notebookservice.dto.response;

import com.notebook.notebookservice.dto.request.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotebookDto {
    private Long id;
    private List<NoteDto> noteDtoList;
}

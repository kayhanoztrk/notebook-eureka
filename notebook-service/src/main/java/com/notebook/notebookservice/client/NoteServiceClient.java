package com.notebook.notebookservice.client;

import com.notebook.notebookservice.dto.request.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@FeignClient(name = "note-service", path="/v1/note")
public interface NoteServiceClient {
    @GetMapping
    ResponseEntity<List<NoteDto>> findAllNotes();

    @GetMapping("/id/{id}")
    ResponseEntity<NoteDto> findNoteById(@PathVariable Long id);

    @GetMapping("/title/{title}")
    ResponseEntity<NoteDto> findNoteByTitle(@PathVariable String title);
}


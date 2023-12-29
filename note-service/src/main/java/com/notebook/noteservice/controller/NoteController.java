package com.notebook.noteservice.controller;

import com.notebook.noteservice.dto.request.NoteCreateRequest;
import com.notebook.noteservice.dto.request.NoteUpdateRequest;
import com.notebook.noteservice.dto.response.NoteDto;
import com.notebook.noteservice.service.NoteService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/v1/note")
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> findAllNotes() {
        List<NoteDto> noteDtoList = noteService.findAllNote();
        return ResponseEntity.ok(noteDtoList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<NoteDto> findNoteById(@PathVariable Long id) {
        NoteDto noteDto = noteService.findNoteById(id);
        return ResponseEntity.ok(noteDto);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<NoteDto> findNoteByTitle(@PathVariable String title) {
        NoteDto noteDto = noteService.findNoteByTitle(title);
        return ResponseEntity.ok(noteDto);
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteCreateRequest noteCreateRequest) {
        NoteDto noteDto = noteService.createNote(noteCreateRequest);
        logger.info("request {} - response {}", noteCreateRequest, noteDto);
        return ResponseEntity.ok(noteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id,
                                              @RequestBody NoteUpdateRequest noteUpdateRequest) {
        NoteDto noteDto = noteService.updateNote(id, noteUpdateRequest);
        logger.info("request {} - response {}", noteUpdateRequest, noteDto);
        return ResponseEntity.ok(noteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        logger.info("request {} ", id);
        return new ResponseEntity<>(id + " idNote has been deleted!",
                HttpStatus.OK);
    }
}

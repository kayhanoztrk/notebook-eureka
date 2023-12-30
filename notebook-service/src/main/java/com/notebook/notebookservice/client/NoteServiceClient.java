package com.notebook.notebookservice.client;

import com.notebook.notebookservice.dto.request.NoteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(NoteServiceClient.class);
    @GetMapping
    ResponseEntity<List<NoteDto>> findAllNotes();

    @GetMapping("/id/{id}")
    @CircuitBreaker(name = "findNoteByIdCircuitBreaker", fallbackMethod = "findNoteByIdFallback")
    ResponseEntity<NoteDto> findNoteById(@PathVariable Long id);

    default ResponseEntity<NoteDto> findNoteByIdFallback(Long id, Exception exception){
        logger.info("Note not found by id " + id);
        return ResponseEntity.ok(NoteDto.builder()
                        .id(3L)
                .title("titleA")
                .description("descriptionA")
                .build());
    }

    @GetMapping("/title/{title}")
    ResponseEntity<NoteDto> findNoteByTitle(@PathVariable String title);
}


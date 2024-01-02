package com.notebook.notebookservice.controller;

import com.notebook.notebookservice.dto.request.NotebookCreateRequest;
import com.notebook.notebookservice.dto.response.NotebookDto;
import com.notebook.notebookservice.dto.response.NotebookResponseDto;
import com.notebook.notebookservice.service.NotebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/v1/notebook")
public class NotebookController {
    private static final Logger logger = LoggerFactory.getLogger(NotebookController.class);
    private final NotebookService notebookService;
    private final Environment environment;

    @Value("${notebook.service.count}")
    private Integer count;

    public NotebookController(NotebookService notebookService, Environment environment) {
        this.notebookService = notebookService;
        this.environment = environment;
    }

    @GetMapping("/notebookId/{id}")
    public ResponseEntity<NotebookDto> findAllNotesInNotebook(@PathVariable Long id) {
        NotebookDto notebookDto = notebookService.findAllNotesInNotebookById(id);
        return ResponseEntity.ok(notebookDto);
    }

    @PostMapping
    public ResponseEntity<NotebookResponseDto> createNotebook() {
        logger.info("Notebook created on port number:", environment.getProperty("server.port"));
        NotebookResponseDto notebookResponseDto = notebookService.createNotebook();
        return ResponseEntity.ok(notebookResponseDto);
    }

    @PostMapping("/addNote")
    public ResponseEntity<NotebookResponseDto> addNoteToNotebook(@RequestBody
                                                                 NotebookCreateRequest notebookCreateRequest){
        NotebookResponseDto notebookResponseDto = notebookService.addNoteToNotebook(notebookCreateRequest);
        return ResponseEntity.ok(notebookResponseDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<NotebookResponseDto> findNotebookById(@PathVariable  Long id){
        NotebookResponseDto notebookResponseDto = notebookService.findNotebookById(id);
        return ResponseEntity.ok(notebookResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> getAllNotebookIdInfo(){
        List<Long> notebookList = notebookService.getAllNotebookIdInfo();
        return ResponseEntity.ok(notebookList);
    }

    @GetMapping("/count")
    public ResponseEntity<String> getCount(){
        return ResponseEntity.ok("notebookService count:" + count);
        //return ResponseEntity.ok("test!");
    }


}

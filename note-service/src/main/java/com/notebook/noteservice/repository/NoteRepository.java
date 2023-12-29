package com.notebook.noteservice.repository;

import com.notebook.noteservice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteByTitle(String title);
}

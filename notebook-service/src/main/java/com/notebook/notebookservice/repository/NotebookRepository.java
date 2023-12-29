package com.notebook.notebookservice.repository;

import com.notebook.notebookservice.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface NotebookRepository extends JpaRepository<Notebook, Long> {
}

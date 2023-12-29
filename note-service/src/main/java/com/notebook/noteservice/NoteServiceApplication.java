package com.notebook.noteservice;

import com.notebook.noteservice.model.Note;
import com.notebook.noteservice.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class NoteServiceApplication implements CommandLineRunner {

	private final NoteRepository noteRepository;

	public NoteServiceApplication(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(NoteServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Note note = new Note();
		note.setTitle("title");
		note.setDescription("description");

		noteRepository.save(note);
	}
}

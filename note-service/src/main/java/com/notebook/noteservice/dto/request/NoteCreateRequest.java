package com.notebook.noteservice.dto.request;

import lombok.*;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreateRequest {
    private String title;
    private String description;
}

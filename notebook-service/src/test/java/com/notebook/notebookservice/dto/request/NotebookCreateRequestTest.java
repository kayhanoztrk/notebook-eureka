package com.notebook.notebookservice.dto.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class NotebookCreateRequestTest {
    private NotebookCreateRequest notebookCreateRequest;

    @Before
    public void setUp(){
        notebookCreateRequest = new NotebookCreateRequest();
        notebookCreateRequest.setNotebookId(1L);
        notebookCreateRequest.setNotes(Arrays.asList("note1"));
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(notebookCreateRequest);
        Assert.assertNotNull(notebookCreateRequest.getNotebookId());
        Assert.assertNotNull(notebookCreateRequest.getNotes());
    }

}

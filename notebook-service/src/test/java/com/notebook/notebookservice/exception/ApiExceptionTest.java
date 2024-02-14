package com.notebook.notebookservice.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class ApiExceptionTest {
    private ApiException apiException;

    @Before
    public void setUp(){
        apiException = new ApiException("message",new Throwable(), ZonedDateTime.now());
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(apiException);
        Assert.assertNotNull(apiException.getMessage());
        Assert.assertNotNull(apiException.getThrowable());
        Assert.assertNotNull(apiException.getZonedDateTime());
    }
}

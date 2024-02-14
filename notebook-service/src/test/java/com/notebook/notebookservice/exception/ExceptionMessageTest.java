package com.notebook.notebookservice.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class ExceptionMessageTest {
    private ExceptionMessage exceptionMessage;

    @Before
    public void setUp(){
        exceptionMessage = new ExceptionMessage("timeStamp",
                200,"error","message","path");
    }

    @Test
    public void testGetter(){
        Assert.assertNotNull(exceptionMessage);
        Assert.assertNotNull(exceptionMessage.timestamp());
        Assert.assertNotNull(exceptionMessage.message());
        Assert.assertNotNull(exceptionMessage.error());
        Assert.assertNotNull(exceptionMessage.path());
    }
}

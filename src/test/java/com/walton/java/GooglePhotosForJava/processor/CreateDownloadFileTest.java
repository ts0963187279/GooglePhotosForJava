package com.walton.java.GooglePhotosForJava.processor;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CreateDownloadFileTest {
    @Test
    public void testExecute() throws IOException {
        CreateDownloadFile createDownloadFile = new CreateDownloadFile();
        File expected = new File("./test");
        Assert.assertEquals(expected,createDownloadFile.execute("test"));
        expected.delete();
        expected = new File("./test/test");
        createDownloadFile = new CreateDownloadFile("test");
        Assert.assertEquals(expected,createDownloadFile.execute("test"));
    }
}

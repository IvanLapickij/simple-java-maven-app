package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testAppConstructor() {
        App app1 = new App();
        App app2 = new App();
        assertEquals(app1.getMessage(), app2.getMessage());
    }

    @Test
    public void testAppMessage()
    {
        App app = new App();
        assertEquals("Hello World!", app.getMessage());
    }

    @Test
    public void testMainPrintsMessage() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outContent));
            App.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        assertEquals("Hello World!" + System.lineSeparator(), outContent.toString());
    }
}

package com.game.view;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ViewWindowTest {

    @Test
    public void testAudioFilesAreFoundAndDoesNotReturnNull() {
        String startGameAudio = "src/resources/images/audio.jpg";
        assertTrue(ViewWindow.getAudioFile(startGameAudio) instanceof BufferedImage);
    }

    @Test
    public void readHTML_returnsString_successfully() {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("[[move]]", "something that replaces the keyword");
        assertEquals("<html>" +
                "<style>" +
                "    html, body{" +
                "        background-color: #EAEAEA;" +
                "        text-align: center;" +
                "        font-size: 3.5em;" +
                "    }" +
                "</style>" +
                "<body>" +
                "<h1>something that replaces the keyword</h1>" +
                "</body>" +
                "</html>", ViewWindow.readHTML("lastMoveBody.html", testMap));
    }
}

package com.game.view;

import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class ViewWindowTest {

    @Test
    public void testAudioFilesAreFoundAndDoesNotReturnNull() {
        String startGameAudio = "src/resources/images/audio.jpg";
        assertTrue(ViewWindow.getAudioFile(startGameAudio) instanceof BufferedImage);
    }
}

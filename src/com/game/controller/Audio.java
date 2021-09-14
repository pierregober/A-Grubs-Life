package com.game.controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Audio implements Runnable{

    /*
     * Enum class for the different room  contain location and audio file path
     */
    private enum AudioPaths{
        GENESIS ("GENESIS","src/resources/music/forest.wav"),
        START ("START","src/resources/music/forest.wav"),
        WEB("WEB","src/resources/music/spider.wav");

        String location;
        String path; //file sound file path for location
        AudioPaths(String location, String path){
            this.location = location;
            this.path = path;
        }

        private String getLocation(){
            return location;
        }

        private String getPath(){
            return path;
        }
    }

    private String musicFilePath;
    private AtomicBoolean exit;
    private static Clip clip;

    public Audio(String musicFilePath) {
        this.musicFilePath = musicFilePath;
        exit = new AtomicBoolean();
        exit.set(false);
    }

    @Override
    public void run() {
        playBackgroundMusic(musicFilePath);
    }

    public void stop()
    {
        clip.stop();
    }

    /*
     *This method will be used to play a sound or music
     */
    public void playBackgroundMusic(String musicFilePath){

        String musicPath = Arrays.stream(
                AudioPaths.values())
                .filter(audioPaths ->
                        audioPaths.getLocation().equalsIgnoreCase(musicFilePath))
                .findAny().orElse(null).getPath();

        try {
            //Get Audio file
            File file = new File(musicPath);

            //Get Clip that will be use to open and play the sound/music
            clip = AudioSystem.getClip();

            //Get the file as an AudioInputStream
            AudioInputStream in = AudioSystem.getAudioInputStream(file);

            //Get the current format of the AudioInputStream
            AudioFormat baseFormat = in.getFormat();

            //Create a new AudioFormat to convert the to from the old format
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),false);

            //Get a new AudioInputStream from the old format to the new format
            AudioInputStream ais = AudioSystem.getAudioInputStream(decodedFormat ,in);

            //Open and start playing the audio stream
            clip.open(ais);

            //Clip will loop the audio until Clip is stop/closed
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            //Close Audio Streams
            in.close();
            ais.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


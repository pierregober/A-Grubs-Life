package com.game.controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio implements Runnable{

    private String musicFilePath;
    private static Clip clip;
    private double volume = 0.99;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Audio(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    @Override
    public void run() {
        playBackgroundMusic(musicFilePath);
    }

    /**
     * Changes volume of background sound, the static clip object in class Audio
     * @param direction UP or DOWN
     */
    public void changeVolume(String direction) {
        double delta = direction.equalsIgnoreCase("UP") ? 0.1 : -0.1;
        double newVol = getVolume() + delta;
        setVolume(newVol);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) ((volumeControl.getMinimum() + newVol * (volumeControl.getMaximum() - volumeControl.getMinimum()))));
    }

    /*
     *This method will be used to play a sound or music
     */
    public static void playBackgroundMusic(String musicFilePath){

        try {
            //Get Audio file
            File file = new File(musicFilePath);
            System.out.println(file.exists());
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

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

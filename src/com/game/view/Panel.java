package com.game.view;
import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
public class Panel {

    JFrame window =  new JFrame("A Grubs Life");

    public Panel() {
        this.window.setSize(1000,800);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
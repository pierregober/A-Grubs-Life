package com.game.view;

import com.game.model.engine.LogicEngine;
import com.game.model.materials.Caterpillar;

import javax.swing.*;
import java.awt.*;

public class ViewWindow {

    private JFrame window;
    private JPanel statPanel;
    private JPanel descriptionPanel;
    private JPanel inputPanel;
    private JLabel statLabel;
    private JTextField inputField;
    private JLabel descriptionLabel;
    private Caterpillar caterpillar;
    private String input;
    private LogicEngine processor;
    public ViewWindow(Caterpillar caterpillar, LogicEngine processor) {
        this.caterpillar = caterpillar;
        this.processor = processor;
        setUpComponents();

    }

    public void welcomeMessage(){
        descriptionLabel.setText("<html>  " +
                "<h1>Welcome to A Grub's Life!</h1><br>" +
                " </html>");
    }
    public void updateCaterpillarStatus(){
        updateDescriptionPanel();
        updateStatPanel();
    }
    public void giveInstructions(){

    }

    private void updateDescriptionPanel(){
        String location = caterpillar.getCurrentLocation().getName().toLowerCase();
        String desc = caterpillar.getCurrentLocation().getDescription().toLowerCase();
        descriptionLabel.setText("<html> "+
                "<h1> " + location + "</h1> <br>" +
                "<h2> " + desc + "</h2>" +
                "</html>");
    }
    private void setUpComponents(){
        setUpInputPanel();
        setUpStatPanel();
        setUpDescriptionPanel();
        setUpWindow();
    }

    private void setUpWindow() {
        this.window = new JFrame("A Grub's Life.");
        this.window.setLayout(new BorderLayout());
        this.window.add(statPanel, BorderLayout.EAST);
        this.window.add(descriptionPanel, BorderLayout.CENTER);
        this.window.add(inputPanel, BorderLayout.SOUTH);

        this.window.setSize(1000,800);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setUpDescriptionPanel() {
        this.descriptionPanel = new JPanel();
        this.descriptionLabel = new JLabel();
        descriptionPanel.setPreferredSize(new Dimension(700,600));
        descriptionPanel.setBackground(Color.lightGray);
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));

        descriptionPanel.add(descriptionLabel);
    }

    private void setUpStatPanel() {
        this.statPanel = new JPanel();
        this.statLabel = new JLabel();
        statPanel.setPreferredSize(new Dimension(300,600));
        statPanel.setLayout(new GridLayout(3,1));
        statLabel.setText("<html>\n" +
                "<style>\n" +
                "table {\n" +
                "color:green;\n" +
                "font-size:20px;\n" +
                "padding:15px;\n" +
                "}\n" +
                "</style>\n" +
                "<table style=\"width:5%\">\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Strength: </td><td>" + caterpillar.getStrength() +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Health: </td><td>" + caterpillar.getHealth() + "/" + caterpillar.getMaxHealth() +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Level: </td><td>" + caterpillar.getLevel() +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</html>");


        statPanel.setBackground(Color.BLACK);
        statPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        statPanel.add(statLabel, Component.TOP_ALIGNMENT);
    }
    private void updateStatPanel(){
        statLabel.setText("<html>\n" +
                "<style>\n" +
                "table {\n" +
                "color:green;\n" +
                "font-size:20px;\n" +
                "padding:15px;\n" +
                "}\n" +
                "</style>\n" +
                "<table style=\"width:5%\">\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Strength: </td><td>" + caterpillar.getStrength() +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Health: </td><td>" + caterpillar.getHealth() +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Level: </td><td>" + caterpillar.getLevel() +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</html>");

    }

    private void setUpInputPanel() {
        this.inputPanel = new JPanel();
        this.inputField = new JTextField(50);
        inputPanel.setPreferredSize(new Dimension(1000,200));
        inputField.setBorder(BorderFactory.createTitledBorder("Enter your command as a [VERB/NOUN]: \n " +
                ""));

        inputField.addActionListener(e -> {
            this.input =  inputField.getText();
            processor.processCommand(getInput());
            inputField.setText("");

        });

        inputPanel.add(inputField);

    }
    public  String getInput(){
        return this.input;
    }


}
package com.game.view;

import com.game.model.engine.LogicEngine;
import com.game.model.materials.Caterpillar;

import javax.swing.*;
import java.awt.*;
import java.security.DigestException;

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
    private JLabel labelVerbs;
    private JLabel labelNouns;
    private JLabel lastMove;

    public ViewWindow(Caterpillar caterpillar, LogicEngine processor) {
        this.caterpillar = caterpillar;
        this.processor = processor;
        setUpComponents();
    }
    public void welcomeMessage(){
        descriptionLabel.setText("<html>  " +
                "<h1>Welcome to A Grub's Life!</h1><br>" +
                "<p> "+ giveInstructions() +"</p>" +
                " </html>");

    }
    public  String getInput(){
        return this.input;
    }
    public void updateCaterpillarStatus(){
            updateLastMove();
            updateDescriptionPanel();
            updateStatPanel();
            this.window.repaint();
    }

    private String giveInstructions(){
        return "You are a newborn caterpillar with three things on his mind.";
    }
    private void updateDescriptionPanel(){
        String location = caterpillar.getCurrentLocation().getName().toLowerCase();
        String desc = caterpillar.getCurrentLocation().getDescription().toLowerCase();
        descriptionLabel.setText("<html> " +
                "<h1> " + location + "</h1> <br>" +
                "<p> " + desc + "</p>" +
                "</html>");
    }
    //==================SETUP METHODS============================
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

        this.window.setPreferredSize(new Dimension(1000,800));
        this.window.setVisible(true);
        this.window.setResizable(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.pack();
    }

    private void setUpDescriptionPanel() {
        this.descriptionPanel = new JPanel();
        this.descriptionLabel = new JLabel();
        descriptionPanel.setPreferredSize(new Dimension(700,600));
        descriptionPanel.setBackground(new Color(255, 255, 255));
        descriptionPanel.setBorder(BorderFactory.createLineBorder(new Color(110, 16, 5)));
        descriptionPanel.add(descriptionLabel);
    }

    private void setUpStatPanel() {
        this.statPanel = new JPanel();
        this.statLabel = new JLabel();
        statPanel.setPreferredSize(new Dimension(300,600));
        statPanel.setLayout(new GridLayout(0,1));
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
                "<td style=\"text-align: left;\">Health: </td><td>" + caterpillar.getHealth()  +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"text-align: left;\">Level: </td><td>" + caterpillar.getLevel() +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</html>");
        statPanel.setBackground(new Color(0, 0, 0));
        statPanel.setBorder(BorderFactory.createTitledBorder("STATS"));
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
        Color background = new Color(10, 80, 20, 158);
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createLineBorder(new Color(110, 16, 5)));
        inputPanel.setBackground(background);
        inputPanel.setPreferredSize(new Dimension(1000,200));
        setUpLabelNouns();
        setUpLabelVerbs();
        setUpInputField();
        setUpLastMove();
        inputPanel.add(inputField, BorderLayout.NORTH);
        inputPanel.add(labelVerbs,BorderLayout.WEST);
        inputPanel.add(labelNouns, BorderLayout.EAST);
        inputPanel.add(lastMove, BorderLayout.CENTER);
    }

    private void setUpLabelNouns(){
        this.labelNouns = new JLabel();
        labelNouns.setText("<html>" +
                "<style>" +
                "li {" +
                "padding-right: 15px;" +
                "}" +
                "</style>" +
                "<body>" +
                "<ul>" +
                "<li>North</li>" +
                "<li>South</li>" +
                "<li>East</li>" +
                "<li>West</li>" +
                "</ul>" +
                "</body></html>");
        labelNouns.setBorder(BorderFactory.createTitledBorder("Directions"));
    }
    private void setUpLabelVerbs(){
        this.labelVerbs = new JLabel();
        labelVerbs.setText("<html>" +
                "<style>" +
                "li {" +
                "padding-right: 50px;" +
                "} " + 
                "</style>" +
                "<body>" +
                "<ul>" +
                "<li>go</li>" +
                "<li>hide</li>" +
                "<li>attack</li>" +
                "<li>eat</li>" +
                "<li>tame</li>" +
                "</ul></body></html>");
        labelVerbs.setBorder(BorderFactory.createTitledBorder("Actions"));
    }
    private void setUpInputField(){
        this.inputField = new JTextField(50);
        inputField.setBorder(BorderFactory.createTitledBorder("Enter your command as a [VERB/NOUN]: \n " +
                ""));
        inputField.setBackground(new Color(217, 224, 214));
        inputField.addActionListener(e -> {

            this.input =  inputField.getText();
            processor.processCommand(getInput());
            inputField.setText("");


        });
    }
    private void setUpLastMove(){
        this.lastMove = new JLabel();
        lastMove.setBorder(BorderFactory.createTitledBorder("Your Last Move"));
        lastMove.setText("<html><body>" +
                "                                  " +
                "<body></html>");
    }
    private void updateLastMove(){
        String lastAction = caterpillar.getLastAction();
        //In here we should add a getLastAction table element, this will let the user know the last thing they sucessfuly did... this variable should be updated in every command process function
        lastMove.setText("<html> "+
                "<h1>" + lastAction +"</h1>" +
                "</html>");
    }


}
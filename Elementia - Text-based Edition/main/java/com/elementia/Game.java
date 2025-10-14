package com.elementia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game {
    JFrame elementia;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, backgroundPanel;
    JLabel titleNameLabel;
    Font titlefont = new Font("Times New Roman", Font.PLAIN, 100);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton;
    JTextArea mainTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();

    public Game() {
        elementia = new JFrame();
        elementia.setSize(800, 600);
        elementia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        elementia.setLayout(null);

        con = elementia.getContentPane();

        // ✅ Background panel with image
        backgroundPanel = new JPanel() {
            BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(new File("src/main/images/ElementiaBG1.png")); // <-- Change path here
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Scale the image to fit the panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 800, 600);
        backgroundPanel.setLayout(null);

        // ✅ Title panel
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setOpaque(false); // make transparent

        titleNameLabel = new JLabel("ELEMENTIA");
        titleNameLabel.setForeground(Color.black);
        titleNameLabel.setFont(titlefont);

        // ✅ Start button panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setOpaque(false);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);

        // Add components to panels
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        // Add everything to background panel
        backgroundPanel.add(titleNamePanel);
        backgroundPanel.add(startButtonPanel);

        // Add background to frame
        con.add(backgroundPanel);

        elementia.setResizable(false);
        elementia.setVisible(true);
    }

    public void createGameScreen() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(new Color(0, 0, 0, 150)); // semi-transparent background
        con.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }


}

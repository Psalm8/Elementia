package main.java.com.elementia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    JFrame elementia;
    Container con;
    JPanel titleNamePanel, buttonPanel, mainTextPanel;
    JLabel titleNameLabel, backgroundLabel;
    Font titlefont = new Font("Times New Roman", Font.BOLD, 100);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, humanVsHumanButton, exitButton;
    JTextArea mainTextArea;
    Image originalBackground;

    TitleScreenHandler tsHandler = new TitleScreenHandler();

    public Game() {

        elementia = new JFrame("Elementia");
        elementia.setSize(1000, 600);
        elementia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        elementia.setLocationRelativeTo(null);
        elementia.setResizable(true);
        elementia.setLayout(null);


        ImageIcon bgIcon = new ImageIcon("src/images/ElementiaBG1.png");
        originalBackground = bgIcon.getImage();
        backgroundLabel = new JLabel(new ImageIcon(originalBackground.getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1000, 600);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 600);


        titleNamePanel = new JPanel();
        titleNamePanel.setOpaque(false);
        titleNameLabel = new JLabel("ELEMENTIA");
        titleNameLabel.setForeground(Color.BLACK);
        titleNameLabel.setFont(titlefont);
        titleNamePanel.add(titleNameLabel);
        layeredPane.add(titleNamePanel, Integer.valueOf(1));


        buttonPanel = new JPanel(new GridLayout(3, 1, 0, 15));
        buttonPanel.setOpaque(false);

        startButton = createStyledButton("START");
        startButton.addActionListener(tsHandler);

        humanVsHumanButton = createStyledButton("HUMAN vs HUMAN");
        humanVsHumanButton.addActionListener(e -> JOptionPane.showMessageDialog(elementia, "Human vs Human Mode!"));
        //You probably don't need to add a message dialog

        exitButton = createStyledButton("EXIT");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(humanVsHumanButton);
        buttonPanel.add(exitButton);
        layeredPane.add(buttonPanel, Integer.valueOf(1));

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        elementia.add(layeredPane);
        elementia.setVisible(true);


        updateLayout(layeredPane);


        elementia.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayout(layeredPane);
            }
        });
    }


    private void updateLayout(JLayeredPane layeredPane) {
        int width = elementia.getWidth();
        int height = elementia.getHeight();


        Image scaledImage = originalBackground.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, width, height);
        layeredPane.setBounds(0, 0, width, height);


        int titleWidth = 700;
        int titleHeight = 150;
        int titleX = (width - titleWidth) / 2;
        int titleY = height / 8;
        titleNamePanel.setBounds(titleX, titleY, titleWidth, titleHeight);


        int buttonWidth = 350;
        int buttonHeight = 250;
        int buttonX = (width - buttonWidth) / 2;
        int buttonY = (height - buttonHeight) / 2 + 50;
        buttonPanel.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        elementia.revalidate();
        elementia.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        return button;
    }

    public void createGameScreen() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(new Color(0, 0, 0, 180));

        mainTextArea = new JTextArea();
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        elementia.getContentPane().removeAll();
        elementia.add(mainTextPanel);
        elementia.revalidate();
        elementia.repaint();
    }

    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }
    //Probably don't need this

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}

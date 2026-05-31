package com.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField guessField;
    private JTextField bestScoreField;
    private JTextField totalGuessesField;

    private JLabel inputLabel;
    private JLabel titleLabel;
    private JLabel statusLabel;
    private JLabel bestScoreLabel;
    private JLabel totalGuessesLabel;
    private JLabel imageLabel;

    private int randomNumber;
    private int guessCount;
    private int bestScore = Integer.MAX_VALUE;

    public Main() {

        randomNumber = (int) (Math.random() * 100) + 1;
        guessCount = 0;

        initializeUI();
    }

    private void initializeUI() {

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.WHITE);

        // Title
        titleLabel = new JLabel("Guess The Number?");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setBounds(100, 60, 250, 30);

        // Input Label
        inputLabel = new JLabel("Enter a number between 1 and 100");
        inputLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        inputLabel.setBounds(90, 95, 250, 20);

        // Status Label
        statusLabel = new JLabel("Try and guess it!");
        statusLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        statusLabel.setBounds(120, 160, 250, 20);

        // Guess Field
        guessField = new JTextField();
        guessField.setBounds(140, 120, 60, 30);

        // Best Score Field
        bestScoreField = new JTextField();
        bestScoreField.setBounds(10, 10, 40, 20);
        bestScoreField.setEditable(false);

        // Best Score Label
        bestScoreLabel = new JLabel("Best Score");
        bestScoreLabel.setBounds(60, 10, 100, 20);

        // Total Guesses Field
        totalGuessesField = new JTextField();
        totalGuessesField.setBounds(10, 40, 40, 20);
        totalGuessesField.setEditable(false);

        // Total Guesses Label
        totalGuessesLabel = new JLabel("Number of Guesses");
        totalGuessesLabel.setBounds(60, 40, 150, 20);

        // Image (Optional)
        imageLabel = new JLabel();
        imageLabel.setBounds(280, 10, 180, 180);

        // Uncomment if image exists
        // imageLabel.setIcon(new ImageIcon("assets/download.png"));

        // Buttons
        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(110, 190, 100, 30);
        guessButton.addActionListener(new GuessButtonListener());

        JButton giveUpButton = new JButton("Give Up");
        giveUpButton.setBounds(50, 240, 100, 30);
        giveUpButton.addActionListener(new GiveUpButtonListener());

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(170, 240, 120, 30);
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        // Add Components
        container.add(titleLabel);
        container.add(inputLabel);
        container.add(statusLabel);
        container.add(guessField);

        container.add(bestScoreField);
        container.add(bestScoreLabel);

        container.add(totalGuessesField);
        container.add(totalGuessesLabel);

        container.add(imageLabel);

        container.add(guessButton);
        container.add(giveUpButton);
        container.add(playAgainButton);

        // Frame Settings
        setTitle("Guess The Number Game");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Guess Button
    private class GuessButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                int userGuess = Integer.parseInt(guessField.getText());

                if (userGuess < 1 || userGuess > 100) {
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Please enter a number between 1 and 100."
                    );
                    return;
                }

                guessCount++;
                totalGuessesField.setText(String.valueOf(guessCount));

                if (userGuess < randomNumber) {
                    statusLabel.setText(userGuess + " is too low. Try again!");
                }
                else if (userGuess > randomNumber) {
                    statusLabel.setText(userGuess + " is too high. Try again!");
                }
                else {

                    statusLabel.setText("🎉 Correct! You Win!");

                    if (guessCount < bestScore) {
                        bestScore = guessCount;
                        bestScoreField.setText(String.valueOf(bestScore));
                    }

                    guessField.setEditable(false);
                }

                guessField.requestFocus();
                guessField.selectAll();

            }
            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        Main.this,
                        "Please enter a valid number."
                );
            }
        }
    }

    // Give Up Button
    private class GiveUpButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            statusLabel.setText("The correct number was: " + randomNumber);
            guessField.setText("");
            guessField.setEditable(false);
        }
    }

    // Play Again Button
    private class PlayAgainButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            randomNumber = (int) (Math.random() * 100) + 1;

            guessCount = 0;

            guessField.setText("");
            totalGuessesField.setText("");

            statusLabel.setText("Try and guess it!");

            guessField.setEditable(true);
            guessField.requestFocus();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Main());
    }
}

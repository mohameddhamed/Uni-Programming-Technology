package assignment2;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame {
    private int[][] clocks;        // Stores the hour of each clock
    private int moves;             // Counts the moves the player has made
    private JLabel[][] clockLabels;  // Stores the JLabels for each clock display
    private JButton[] buttons;     // Buttons to control the game
    private JLabel movesLabel;     // Display moves
    private final int GRID_SIZE = 3;

    public Game() {
        initializeGame();
        initializeUI();
    }

    // Initializes the clocks with random times
    private void initializeGame() {
        clocks = new int[GRID_SIZE][GRID_SIZE];
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                clocks[i][j] = random.nextInt(12) + 1;  // Random hour between 1 and 12
            }
        }
        moves = 0;
    }

    // Sets up the game interface
    // private void initializeUI() {
    //     setTitle("Rubik Clock Game");
    //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     setLayout(new BorderLayout());
        
    //     JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
    //     JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        
    //     // Set up clock display
    //     clockLabels = new JLabel[GRID_SIZE][GRID_SIZE];
    //     for (int i = 0; i < GRID_SIZE; i++) {
    //         for (int j = 0; j < GRID_SIZE; j++) {
    //             JLabel clockLabel = new JLabel(String.valueOf(clocks[i][j]), SwingConstants.CENTER);
    //             clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
    //             clockLabels[i][j] = clockLabel;
    //             gridPanel.add(clockLabel);
    //         }
    //     }
        
    //     // Set up control buttons
    //     buttons = new JButton[4];
    //     for (int i = 0; i < buttons.length; i++) {
    //         JButton button = new JButton("Button " + (i + 1));
    //         final int buttonIndex = i;
    //         button.addActionListener(e -> handleButtonPress(buttonIndex));
    //         buttons[i] = button;
    //         buttonPanel.add(button);
    //     }

    //     // Display moves count
    //     movesLabel = new JLabel("Moves: 0", SwingConstants.CENTER);
    //     movesLabel.setFont(new Font("Arial", Font.BOLD, 16));
    //     add(movesLabel, BorderLayout.NORTH);
        
    //     // Add panels to the frame
    //     add(gridPanel, BorderLayout.CENTER);
    //     add(buttonPanel, BorderLayout.SOUTH);
        
    //     setSize(400, 400);
    //     setVisible(true);
    // }
    // Sets up the game interface
    // private void initializeUI() {
    //     setTitle("Rubik Clock Game");
    //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     setLayout(new GridBagLayout());
    //     GridBagConstraints gbc = new GridBagConstraints();
        
    //     // Create and add the clock labels
    //     clockLabels = new JLabel[GRID_SIZE][GRID_SIZE];
    //     for (int i = 0; i < GRID_SIZE; i++) {
    //         for (int j = 0; j < GRID_SIZE; j++) {
    //             clockLabels[i][j] = new JLabel(String.valueOf(clocks[i][j]), SwingConstants.CENTER);
    //             clockLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
    //             gbc.gridx = j;
    //             gbc.gridy = i;
    //             gbc.insets = new Insets(10, 10, 10, 10);  // Space between clocks
    //             add(clockLabels[i][j], gbc);
    //         }
    //     }

    //     // Create and add the buttons between clocks
    //     buttons = new JButton[4];
    //     for (int i = 0; i < 4; i++) {
    //         final int buttonIndex = i;
    //         buttons[i] = new JButton("Button " + (i + 1));
    //         buttons[i].addActionListener(e -> handleButtonPress(buttonIndex));

    //         // Position buttons at the 4 spots between clocks
    //         gbc.gridx = (i % 2 == 0) ? 0 : 2;  // Buttons in first and third columns
    //         gbc.gridy = (i < 2) ? 0 : 2;       // Buttons in first and third rows
    //         gbc.insets = new Insets(0, 0, 0, 0);  // No extra space around buttons
    //         add(buttons[i], gbc);
    //     }

    //     // Display moves count
    //     movesLabel = new JLabel("Moves: 0", SwingConstants.CENTER);
    //     movesLabel.setFont(new Font("Arial", Font.BOLD, 16));
    //     gbc.gridx = 1; // Centered in the middle
    //     gbc.gridy = 3; // Positioned below the grid
    //     gbc.gridwidth = 3; // Span across the entire grid
    //     add(movesLabel, gbc);

    //     // Frame settings
    //     setSize(400, 400);
    //     setVisible(true);
    // }

    private void initializeUI() {
        setTitle("Rubik Clock Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        // Initialize clocks (you'll need a method to initialize these with appropriate data)
        clocks = new int[GRID_SIZE][GRID_SIZE];  // Assuming clocks array is 5x5
        clockLabels = new JLabel[GRID_SIZE][GRID_SIZE];

        // Create and add the clock labels
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // Only place clocks on the outer edges of the grid
                if (i == 0 || i == GRID_SIZE - 1 || j == 0 || j == GRID_SIZE - 1) {
                    clockLabels[i][j] = new JLabel(String.valueOf(clocks[i][j]), SwingConstants.CENTER);
                    clockLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                    add(clockLabels[i][j]);
                } else {
                    // Add an empty space for button positions
                    add(new JLabel(""));  // Placeholder for non-clock positions
                }
            }
        }

        // Create and add the buttons
        buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            final int buttonIndex = i;
            buttons[i] = new JButton("Button " + (i + 1));
            buttons[i].addActionListener(e -> handleButtonPress(buttonIndex));
            // buttons[i].addActionListener(new ActionListener() {

            // Place buttons in the middle of the grid (between clocks)
            if (i == 0) {
                add(buttons[i]);  // Position 1: Row 1, Column 1
            } else if (i == 1) {
                add(buttons[i]);  // Position 2: Row 1, Column 3
            } else if (i == 2) {
                add(buttons[i]);  // Position 3: Row 3, Column 1
            } else {
                add(buttons[i]);  // Position 4: Row 3, Column 3
            }
        }

        // Display moves count
        movesLabel = new JLabel("Moves: 0", SwingConstants.CENTER);
        movesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(movesLabel);

        // Frame settings
        setSize(500, 500);
        setVisible(true);
    }


    // Handles the clock update logic when a button is pressed
    private void handleButtonPress(int buttonIndex) {
        // System.out.println("Pressed");
        // JOptionPane.showMessageDialog(this, "Congratulations! You solved it in " + moves + " moves!");
        int[][] affectedClocks = {{0, 0}, {0, 2}, {2, 0}, {2, 2}}; // Corners affected by each button
        int row = affectedClocks[buttonIndex][0];
        int col = affectedClocks[buttonIndex][1];
        
        incrementClock(row, col);      // Increment center clock of the button
        incrementClock(row, (col + 1) % GRID_SIZE); // Right clock
        incrementClock((row + 1) % GRID_SIZE, col); // Bottom clock
        incrementClock((row + 1) % GRID_SIZE, (col + 1) % GRID_SIZE); // Bottom-right clock
        
        moves++;
        movesLabel.setText("Moves: " + moves);

        updateClockDisplay(); // Update UI after each button press

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You solved it in " + moves + " moves!");
            initializeGame();
            movesLabel.setText("Moves: 0");
        }
        
        repaint();
    }

    // Increment the hour of the clock and wrap around after 12
    private void incrementClock(int row, int col) {
        clocks[row][col] = clocks[row][col] % 12 + 1;
    }
    // Update the JLabel display to match the clocks array
    private void updateClockDisplay() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                clockLabels[i][j].setText(String.valueOf(clocks[i][j]));
            }
        }
    }

    // Check if all clocks show 12
    private boolean checkWin() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (clocks[i][j] != 12) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}


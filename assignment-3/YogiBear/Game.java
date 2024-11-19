package YogiBear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame {
    private GameGrid gridPanel;
    private Player player;
    private JLabel livesLabel;
    private int basketCount; // Counter for collected baskets
    private JLabel basketLabel; // Label to display basket count
    private JLabel timerLabel; // Label to display timer
    private Timer gameTimer; // Timer to count elapsed time
    private int elapsedTime; // Elapsed time in seconds

    public Game() {
        player = new Player();
        gridPanel = new GameGrid();
        basketCount = 0;
        elapsedTime = 0;

        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.CENTER);

        // Initialize and add labels
        basketLabel = new JLabel("Baskets Collected: " + basketCount);
        timerLabel = new JLabel("Time: 0s");
        livesLabel = new JLabel("Lives: " + player.getLives());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 2));
        infoPanel.add(basketLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(livesLabel);

        add(infoPanel, BorderLayout.SOUTH);

        setTitle("Yogi Bear Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // Set up the timer to update every second
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                timerLabel.setText("Time: " + elapsedTime + "s");
                moveRangers(); // Move rangers every second
                checkForRangerProximity();
            }
        });
        gameTimer.start(); // Start the timer when the game starts

        // Set up key listener for player movement
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: // Up
                    case KeyEvent.VK_K: // Up
                        movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_S: // Down
                    case KeyEvent.VK_J: // Down
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_A: // Left
                    case KeyEvent.VK_H: // Left
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_D: // Right
                    case KeyEvent.VK_L: // Right
                        movePlayer(0, 1);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) gridPanel.getGraphics();

        int cellSize = 50;
        g2.setColor(Color.RED);
        g2.fillOval(player.getCol() * cellSize + 10, player.getRow() * cellSize + 10, cellSize - 20, cellSize - 20);
    }

    public static void main(String[] args) {
        new Game();
    }

    // Handle player movement
    private void movePlayer(int dx, int dy) {
        int newX = player.getRow() + dx;
        int newY = player.getCol() + dy;

        // Check if the new position is walkable
        if (isWalkable(newX, newY)) {
            player.setRow(newX);
            player.setCol(newY);

            // Check if the player collected a basket
            if (gridPanel.getCell(newX, newY) == 'B') {
                basketCount++;
                basketLabel.setText("Baskets Collected: " + basketCount);
                gridPanel.setCell(newX, newY, 'E'); // Remove basket from grid
            }
        }
        // Refresh the display
        repaint();
    }
    private void moveRangers() {
        for (Ranger ranger : gridPanel.getRangers()) {
            if (Math.random() < 0.5) ranger.moveHorizontal(gridPanel.getGrid()[0].length);
            else ranger.moveVertical(gridPanel.getGrid().length);
        }
        repaint();
    }
    private void checkForRangerProximity() {
        for (Ranger ranger : gridPanel.getRangers()) {
            if (Math.abs(ranger.getRow() - player.getRow()) <= 1 &&
                Math.abs(ranger.getCol() - player.getCol()) <= 1) {
                player.loseLife();
                livesLabel.setText("Lives: " + player.getLives());

                if (player.getLives() > 0) {
                    player.setRow(0);
                    player.setCol(0);
                } else {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(this, "Game Over! You've lost all lives.");
                }
                break;
            }
        }
    }

    // Check if the position (x, y) is walkable
    private boolean isWalkable(int x, int y) {
        // Ensure the position is within the bounds of the map
        if (x < 0 || y < 0 || x >= gridPanel.getGrid().length || y >= gridPanel.getGrid()[0].length) {
            return false; // Out of bounds
        }
        // Check if the position is occupied by a tree or mountain
        return !(gridPanel.getGrid()[x][y] == 'T' || gridPanel.getGrid()[x][y] == 'M');
    }
}

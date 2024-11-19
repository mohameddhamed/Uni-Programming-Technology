package YogiBear;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameGrid extends JPanel {
    private final int rows = 10; // Number of rows
    private final int cols = 10; // Number of columns
    private final int cellSize = 50; // Size of each cell
    private char[][] grid; // Grid representation ('E': empty, 'B': basket, 'T': tree)
    private ArrayList<Ranger> rangers = new ArrayList<>(); // List of rangers
    private int initializedBasketCount;


    public GameGrid(int level) { // Accept level as parameter
        this.grid = new char[rows][cols];
        this.rangers = new ArrayList<>();
        initializeGrid(level);
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    private void initializeGrid(int level) {
        Random rand = new Random();
        initializedBasketCount = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double randomValue = Math.random();

                if (randomValue < 0.1 + (0.02 * level)) { // Increase obstacles with levels
                    grid[row][col] = rand.nextBoolean() ? 'T' : 'M'; // Tree or Mountain
                } else if (randomValue < 0.2 + (0.05 * level)) { // Increase baskets with levels
                    grid[row][col] = 'B';
                    initializedBasketCount++;
                } else {
                    grid[row][col] = 'E'; // Empty
                }
            }
        }
        grid[0][0] = 'E'; // Starting position

        // Add more rangers as levels progress
        for (int i = 0; i < 2 + level; i++) {
            int rRow = rand.nextInt(rows);
            int rCol = rand.nextInt(cols);
            rangers.add(new Ranger(rRow, rCol));
        }
    }
    public int initializedBaskets() {
        return initializedBasketCount;
    }


    public char getCell(int row, int col) {
        return grid[row][col];
    }
    public char[][] getGrid() {
        return grid;
    }

    public void setCell(int row, int col, char value) {
        grid[row][col] = value;
    }
    public ArrayList<Ranger> getRangers() {
        return rangers;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                char cell = grid[row][col];

                if (cell == 'T') {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, cellSize, cellSize); // Draw trees
                } else if (cell == 'M') {
                    g.setColor(Color.GRAY);
                    g.fillRect(x, y, cellSize, cellSize); // Draw trees
                } else if (cell == 'B') {
                    g.setColor(Color.YELLOW);
                    g.fillOval(x + 10, y + 10, cellSize - 20, cellSize - 20); // Draw basket
                }
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize); // Draw grid lines
            }
        }
        // Draw rangers
        for (Ranger ranger : rangers) {
            int x = ranger.getCol() * cellSize;
            int y = ranger.getRow() * cellSize;
            g.setColor(Color.BLUE);
            g.fillRect(x + 10, y + 10, cellSize - 20, cellSize - 20); // Draw ranger
        }
    }
}

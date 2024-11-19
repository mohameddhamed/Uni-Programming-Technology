package YogiBear;

public class Player {
    private int row, col; // Player's position
    private int lives;

    public Player() {
        this.row = 0; // Start position
        this.col = 0;
        this.lives = 3; // Start with 3 lives
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public void moveUp() {
        if (row > 0) row--;
    }

    public void moveDown() {
        if (row < 9) row++;
    }

    public void moveLeft() {
        if (col > 0) col--;
    }

    public void moveRight() {
        if (col < 9) col++;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        lives--;
    }
}

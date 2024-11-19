package YogiBear;

public class Ranger {
    private int row, col; // Ranger's position

    public Ranger(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void moveHorizontal(int maxCols) {
        // Move horizontally by 1 step
        col += (col + 1 < maxCols) ? 1 : -1;
    }

    public void moveVertical(int maxRows) {
        // Move vertically by 1 step
        row += (row + 1 < maxRows) ? 1 : -1;
    }
}

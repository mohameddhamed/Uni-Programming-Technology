package YogiBear;

public class Ranger {
    private int row, col; // Ranger's position

    public Ranger(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
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
    public static boolean isWalkable(GameGrid gridPanel, int x, int y) {
        if (x < 0 || y < 0 || x >= gridPanel.getGrid().length || y >= gridPanel.getGrid()[0].length) {
            return false; // Out of bounds
        }
        // if (gridPanel.getGrid()[x][y] == 'T' || gridPanel.getGrid()[x][y] == 'M') {
        //     return false; // Obstacle
        // }
        for (Ranger ranger : gridPanel.getRangers()) {
            if (ranger.getRow() == x && ranger.getCol() == y) {
                return false; // Position occupied by another ranger
            }
        }
        return true;
    }

}

package Model;

public class Board {
    private int[][] field;
    public int rows;
    public int cols;

    public Board(int rows, int cols) {
        this.field = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public void setField(int col, int mark) {
        for (int i = rows - 1; i >= 0; i--) {
            if (field[i][col] == 0) {
                field[i][col] = mark;
                break;
            }
        }
    }

    public void removeField(int col) {
        for (int i = 0; i < rows; i++) {
            if (field[i][col] != 0) {
                field[i][col] = 0;
                break;
            }
        }
    }

    public int getField(int row, int col) {
        return field[row][col];
    }

    public boolean isFull() {
        for (int[] row: field) {
            for (int cell: row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hasWinner() {
        int rowWinner = hasRow();
        if (rowWinner != 0) {
            return rowWinner;
        }
        int columnWinner = hasColumn();
        if (columnWinner != 0) {
            return columnWinner;
        }
        int diagonalWinner = hasDiagonal();
        if (diagonalWinner != 0) {
            return diagonalWinner;
        }
        return 0;
    }

    private int hasRow() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (field[i][j] != 0 && field[i][j] == field[i][j+1] && field[i][j+1] == field[i][j+2] && field[i][j+2] == field[i][j+3]) {
                    return field[i][j];
                }
            }
        }
        return 0;
    }

    private int hasColumn() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] != 0 && field[i][j] == field[i + 1][j] && field[i + 1][j] == field[i + 2][j] && field[i + 2][j] == field[i + 3][j]) {
                    return field[i][j];
                }
            }
        }
        return 0;
    }

    private int hasDiagonal() {
        // Top left to right bottom
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (field[i][j] != 0 && field[i][j] == field[i+1][j+1] && field[i+1][j+1] == field[i+2][j+2] && field[i+2][j+2] == field[i+3][j+3]) {
                    return field[i][j];
                }
            }
        }

        // Top bottom to right top
        for (int i = rows - 1; i >= 3; i--) {
            for (int j = 0; j < cols - 3; j++) {
//                System.out.println(i + " " + j + "|" + (i-1) + " " + (j+1) + "|"+ (i-2) + " " + (j+2) + "|"+ (i-3) + " " + (j+3) + "|");
                if (field[i][j] != 0 && field[i][j] == field[i-1][j+1] && field[i-1][j+1] == field[i-2][j+2] && field[i-2][j+2] == field[i-3][j+3]) {
                    return field[i][j];
                }
            }
        }
        return 0;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        StringBuilder colNumbers = new StringBuilder();
        StringBuilder seperator = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            colNumbers.append(i).append(" ");
            seperator.append("- ");
        }
        result.append(colNumbers).append("\n").append(seperator).append("\n");
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                line.append(field[i][j]).append(" ");
            }
            line.append("\n");
            result.append(line);
        }
        return result.toString();
    }
}

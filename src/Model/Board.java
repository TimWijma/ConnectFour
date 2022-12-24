package Model;

public class Board {
    public int[][] field;
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
        int[][] rows = getRows();
        for(int[] row: rows) {
            for (int i = 1; i < 3; i++) {
                if (row[i] == 4) {
                    return i;
                }
            }
        }

        int[][] columns = getColumns();
        for(int[] col: columns) {
            for (int i = 1; i < 3; i++) {
                if (col[i] == 4) {
                    return i;
                }
            }
        }

        int[][] diagonals = getDiagonals();
        for(int[] diagonal: diagonals) {
            for (int i = 1; i < 3; i++) {
                if (diagonal[i] == 4) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int[][] getRows() {
        int[][] result = new int[(cols - 3) * rows][3]; // Total amount of possible rows without index out of bounds
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                for (int count = 0; count <= 3; count++) {
                    result[i * (rows - 2) + j][field[i][j + count]]++;
                }
                // i * (rows - 2) + j = length of result
                // field[i][j + count] increases the corresponding value, 0 = empty, 1 = player1, 2 = player2
            }
        }
        return result;
    }

    public int[][] getColumns() {
        int[][] result = new int[(rows - 3) * cols][3]; // Total amount of possible columns without index out of bounds
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols; j++) {
                for (int count = 0; count <= 3; count++) {
                    result[i * (rows + 1) + j][field[i + count][j]]++;
                }
                // i * (rows + 1) + j = length of result
                // field[i + count][j] increases the corresponding value, 0 = empty, 1 = player1, 2 = player2
            }
        }
        return result;
    }

    public int[][] getDiagonals() {
        int[][] result = new int[(rows - 3) * (cols - 3) * 2][3]; // Total amount of possible columns without index out of bounds
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                for (int count = 0; count <= 3; count++) {
                    result[i * (rows - 2) + j][field[i + count][j + count]]++;
                }
                // i * (rows + 1) + j = length of result
                // field[i + count][j +count] increases the corresponding value, 0 = empty, 1 = player1, 2 = player2
            }
        }

        for (int i = rows - 1;i >= 3; i--) {
            for (int j = 0; j < cols - 3; j++) {
                int x = i == 3 ? 17 : 12;
                for (int count = 0; count <= 3; count++) {
                    result[j + (i % 5) + x][field[i - count][j + count]]++;
                }
                // x = 12 to resume last loop, 12 + 5 if i == 3 because of the formula
                // i * (rows + 1) + j = length of result
                // field[i + count][j +count] increases the corresponding value, 0 = empty, 1 = player1, 2 = player2
            }
        }

        return result;
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

    public void print() {
        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < cols; j++) {
                if (field[i][j] == 0) {
                    sb.append("\u001b[37m 0 \u001B[0m");
                } else if (field[i][j] == 1) {
                    sb.append("\u001B[31m 1 \u001B[0m");
                } else {
                    sb.append("\u001B[33m 2 \u001B[0m");
                }
            }
            System.out.println(sb);
        }
    }
}

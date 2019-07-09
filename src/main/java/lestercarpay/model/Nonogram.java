package lestercarpay.model;


public class Nonogram {
    private Cell[][] puzzle;

    private Specification[] rowSpecifications;
    private Specification[] columnSpecifications;

    public Nonogram(int nRows, int nColumns) {
        this.puzzle = new Cell[nRows][nColumns];
        initialisePuzzleToEmpty();

        this.rowSpecifications = new Specification[nRows];
        this.columnSpecifications = new Specification[nColumns];
        initialiseSpecificationsToZero();
    }

    private void initialisePuzzleToEmpty() {
        for (int rowNumber = 0; rowNumber < puzzle.length; rowNumber++) {
            for (int columnNumber = 0; columnNumber < puzzle[rowNumber].length; columnNumber++) {
                puzzle[rowNumber][columnNumber] = Cell.EMPTY;
            }
        }
    }

    private void initialiseSpecificationsToZero() {
        for (int rowNumber = 0; rowNumber < this.rowSpecifications.length; rowNumber++) {
            rowSpecifications[rowNumber] = new Specification();
        }
        for (int columnNumber = 0; columnNumber < this.columnSpecifications.length; columnNumber++) {
            columnSpecifications[columnNumber] = new Specification();
        }
    }

    public int getNRows() {
        return puzzle.length;
    }

    public int getNColumns() {
        return puzzle[0].length;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Cell[] row : puzzle) {
            for (Cell cell : row) {
                result.append(cellCharacter(cell));
            }
            result.append('\n');
        }
        return result.toString();
    }

    private char cellCharacter(Cell cell) {
        if (cell == Cell.FILLED) {
            return 'X';
        }
        return 'O';
    }
}
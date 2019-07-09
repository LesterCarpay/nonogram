package lestercarpay.model;


import java.util.List;

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

    public Cell[] getRow(int rowNumber) {
        return puzzle[rowNumber];
    }

    public Cell[] getColumn(int columnNumber) {
        Cell[] column = new Cell[getNRows()];
        for (int rowNumber = 0; rowNumber < getNRows(); rowNumber++) {
            column[rowNumber] = getRow(rowNumber)[columnNumber];
        }
        return column;
    }

    public int getNRows() {
        return puzzle.length;
    }

    public int getNColumns() {
        return puzzle[0].length;
    }

    public Cell getCell(int row, int column) {
        return puzzle[row][column];
    }

    public void setCell(int row, int column, Cell value) {
        this.puzzle[row][column] = value;
    }

    public Specification getRowSpecification(int row) {
        return rowSpecifications[row];
    }

    public void setRowSpecification(int row, int... values) {
        rowSpecifications[row] = new Specification(values);
    }

    public Specification getColumnSpecification(int column) {
        return columnSpecifications[column];
    }

    public void setColumnSpecifications(int column, int... values) {
        columnSpecifications[column] = new Specification(values);
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
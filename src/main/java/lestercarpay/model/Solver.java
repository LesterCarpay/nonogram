package lestercarpay.model;


public class Solver {
    private Nonogram puzzle;

    public Solver(Nonogram puzzle) {
        this.puzzle = puzzle;
    }

    public void solve() {
        improve();
    }

    public void improve() {
        for (int row = 0; row < puzzle.getNRows(); row++) {
            SetCellFinder setCellFinder = new SetCellFinder(puzzle.getRow(row), puzzle.getRowSpecification(row));
            puzzle.setRow(row, setCellFinder.findSetCells());
        }
        for (int column = 0; column < puzzle.getNColumns(); column++) {
            SetCellFinder setCellFinder = new SetCellFinder(puzzle.getColumn(column), puzzle.getColumnSpecification(column));
            puzzle.setColumn(column, setCellFinder.findSetCells());
        }
    }

    public boolean isSolved() {
        return calculateActualNumberOfFilledSquares() == calculateExpectedNumberOfFilledSquares();
    }

    private int calculateExpectedNumberOfFilledSquares() {
        int result = 0;
        for (int row = 0; row < puzzle.getNRows(); row++) {
            for (int block : puzzle.getRowSpecification(row).getBlocks()) {
                result += block;
            }
        }
        return result;
    }

    private int calculateActualNumberOfFilledSquares() {
        int result = 0;
        for (int row = 0; row < puzzle.getNRows(); row++) {
            for (int column = 0; column < puzzle.getNColumns(); column++) {
                if (puzzle.getCell(row, column) == Cell.FILLED) {
                    result++;
                }
            }
        }
        return result;
    }
}

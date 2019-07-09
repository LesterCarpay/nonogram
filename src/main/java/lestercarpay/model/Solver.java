package lestercarpay.model;

public class Solver {
    private Nonogram puzzle;

    public Solver(Nonogram puzzle) {
        this.puzzle = puzzle;
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
}

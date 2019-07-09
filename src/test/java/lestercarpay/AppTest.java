package lestercarpay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lestercarpay.model.Cell;
import lestercarpay.model.Nonogram;
import lestercarpay.model.SetCellFinder;
import lestercarpay.model.Specification;
import org.junit.Test;


public class AppTest 
{

    private Nonogram buildTestPuzzle() {
        Nonogram puzzle = new Nonogram(3,5);
        puzzle.setCell(1, 2, Cell.FILLED);
        puzzle.setCell(0, 0, Cell.CROSSED);

        puzzle.setRowSpecification(1,1,2);
        puzzle.setColumnSpecifications(2,1,1);

        return puzzle;
    }

    @Test
    public void checkRowsAndColumns() {
        Nonogram puzzle = buildTestPuzzle();

        assertTrue( puzzle.getNRows() == 3 && puzzle.getNColumns() == 5);

        Cell[] expectedColumn = {Cell.EMPTY, Cell.FILLED, Cell.EMPTY};
        assertEquals(expectedColumn, puzzle.getColumn(2));
    }

    @Test
    public void checkPuzzleBuilding() {
        Nonogram puzzle = buildTestPuzzle();

        String expectedPuzzleString =
                        "OOOOO\n" +
                        "OOXOO\n" +
                        "OOOOO\n";
        assertEquals(expectedPuzzleString, puzzle.toString());
    }

    @Test
    public void checkMinimumLength() {
        Nonogram puzzle = buildTestPuzzle();

        assertEquals(4, puzzle.getRowSpecification(1).getMinimumLength());
        assertEquals(3, puzzle.getColumnSpecification(2).getMinimumLength());
    }

    @Test
    public void testSetCellFinder() {
        Nonogram puzzle = buildTestPuzzle();

        SetCellFinder setCellFinder = new SetCellFinder(puzzle.getRow(1), new Specification(1,2));
        Cell[] expectedSetCells = {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.EMPTY};
        assertEquals(expectedSetCells, setCellFinder.findNewSetCells());
    }
}

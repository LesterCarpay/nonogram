package lestercarpay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lestercarpay.model.*;
import org.junit.Test;


public class AppTest 
{

    private Nonogram buildTestPuzzle() {
        Nonogram puzzle = new Nonogram(3,5);
        puzzle.setCell(1, 2, Cell.FILLED);
        puzzle.setCell(0, 0, Cell.CROSSED);

        puzzle.setRowSpecification(1,1,2);
        puzzle.setColumnSpecification(2,1,1);

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
    public void testSetCellFinderForEmptySection() {
        Nonogram puzzle = buildTestPuzzle();

        SetCellFinder setCellFinder = new SetCellFinder(puzzle.getRow(2), new Specification(1,2));
        Cell[] expectedSetCells = {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.EMPTY};
        assertEquals(expectedSetCells, setCellFinder.findSetCells());
    }

    @Test
    public void testSetCellFinderForSectionWithFilledCell() {
        Nonogram puzzle = buildTestPuzzle();

        SetCellFinder setCellFinder = new SetCellFinder(puzzle.getRow(1), new Specification(1,2));
        Cell[] expectedSetCells = {Cell.FILLED, Cell.CROSSED, Cell.FILLED, Cell.FILLED, Cell.CROSSED};
        assertEquals(expectedSetCells, setCellFinder.findSetCells());
    }

    @Test
    public void testSetCellFinderForSectionWithCrossedCell() {
        Nonogram puzzle = buildTestPuzzle();

        SetCellFinder setCellFinder = new SetCellFinder(puzzle.getRow(0), new Specification(1,2));
        Cell[] expectedSetCells = {Cell.CROSSED, Cell.FILLED, Cell.CROSSED, Cell.FILLED, Cell.FILLED};
        assertEquals(expectedSetCells, setCellFinder.findSetCells());
    }

    @Test
    public void testSolver() {
        Nonogram puzzle = new Nonogram(5,5);
        puzzle.setRowSpecification(0,5);
        puzzle.setRowSpecification(1,2,2);
        puzzle.setRowSpecification(2,1,1);
        puzzle.setRowSpecification(3,2,2);
        puzzle.setRowSpecification(4,5);

        puzzle.setColumnSpecification(0,5);
        puzzle.setColumnSpecification(1,2,2);
        puzzle.setColumnSpecification(2,1,1);
        puzzle.setColumnSpecification(3,2,2);
        puzzle.setColumnSpecification(4,5);

        Solver solver = new Solver(puzzle);
        solver.improve();
        String expected = "XXXXX\n" +
                "XXOXX\n" +
                "XOOOX\n" +
                "XXOXX\n" +
                "XXXXX\n";
        assertEquals(puzzle.toString(), expected);
    }

    @Test
    public void testSpecificationFromString() {
        Specification specification = new Specification("1 2 1");

        assertEquals(1 , specification.getBlocks().get(0).intValue());
        assertEquals(2 , specification.getBlocks().get(1).intValue());
        assertEquals(1 , specification.getBlocks().get(2).intValue());
    }
}

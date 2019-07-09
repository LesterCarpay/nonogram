package lestercarpay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lestercarpay.model.Cell;
import lestercarpay.model.Nonogram;
import org.junit.Test;


public class AppTest 
{

    public Nonogram buildTestPuzzle() {
        Nonogram puzzle = new Nonogram(3,5);
        puzzle.setCell(1, 2, Cell.FILLED);
        puzzle.setCell(0, 0, Cell.CROSSED);

        return puzzle;
    }

    @Test
    public void checkRowsAndColumns() {
        Nonogram puzzle = buildTestPuzzle();

        assertTrue( puzzle.getNRows() == 3 && puzzle.getNColumns() == 5);
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
}

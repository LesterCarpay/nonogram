package lestercarpay;


import lestercarpay.model.Cell;
import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;

public class Main {
    public static void main( String[] args ) {
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
        System.out.println(puzzle);
    }
}
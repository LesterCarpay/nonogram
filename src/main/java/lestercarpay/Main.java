package lestercarpay;


import lestercarpay.model.Nonogram;

public class Main {
    public static void main( String[] args )
    {
        Nonogram puzzle = new Nonogram(3,5);
        System.out.println("Number of rows is: " + puzzle.getNRows());
        System.out.println("Number of columns is: " + puzzle.getNColumns());
        System.out.println("Puzzle is:");
        System.out.println(puzzle);
    }
}
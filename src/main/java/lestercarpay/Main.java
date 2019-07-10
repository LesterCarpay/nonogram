package lestercarpay;


import lestercarpay.controller.SolveButton;
import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;
import lestercarpay.view.NonogramPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void openNonogramSolverWindow(Nonogram puzzle) {
        Solver solver = new Solver(puzzle);

        JFrame frame = new JFrame("Nonogram Solver");

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new SolveButton(solver, puzzle));
        frame.setJMenuBar(menuBar);

        NonogramPanel panel = new NonogramPanel(puzzle);
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(750, 750));
        frame.pack();
        frame.setLocationRelativeTo (null); // Center on screen.
        frame.setVisible(true);
    }

    public static Nonogram swissFlag() {
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

        return puzzle;
    }

    public static Nonogram computer() {
        Nonogram puzzle = new Nonogram(10,10);
        puzzle.setRowSpecification(0,1,1,6);
        puzzle.setRowSpecification(1,1,2,1);
        puzzle.setRowSpecification(2,1,1,1,2,1);
        puzzle.setRowSpecification(3,1,2,2,1);
        puzzle.setRowSpecification(4,5,1);
        puzzle.setRowSpecification(5,7);
        puzzle.setRowSpecification(6,2,1);
        puzzle.setRowSpecification(7,2,1,1,2);
        puzzle.setRowSpecification(8,2,2);
        puzzle.setRowSpecification(9,8);

        puzzle.setColumnSpecification(0,1,1,1,2);
        puzzle.setColumnSpecification(1,1,2,3);
        puzzle.setColumnSpecification(2,1,1,1,2,1);
        puzzle.setColumnSpecification(3,1,4,1);
        puzzle.setColumnSpecification(4,6,1,1);
        puzzle.setColumnSpecification(5,1,1,1);
        puzzle.setColumnSpecification(6,1,2,1,1,1);
        puzzle.setColumnSpecification(7,1,2,1,2);
        puzzle.setColumnSpecification(8,1,1,2);
        puzzle.setColumnSpecification(9,8);

        return puzzle;
    }

    public static void main( String[] args ) {
        Nonogram puzzle = computer();

        openNonogramSolverWindow(puzzle);

    }
}
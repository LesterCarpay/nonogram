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
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setLocationRelativeTo (null); // Center on screen.
        frame.setVisible(true);
    }

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

        openNonogramSolverWindow(puzzle);

    }
}
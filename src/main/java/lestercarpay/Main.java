package lestercarpay;


import lestercarpay.controller.MouseTracker;
import lestercarpay.controller.SolveButton;
import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;
import lestercarpay.view.NonogramPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static void openNonogramSolverWindow(Nonogram puzzle) {
        Solver solver = new Solver(puzzle);

        JFrame frame = new JFrame("Nonogram Solver");

        NonogramPanel panel = new NonogramPanel(puzzle);
        frame.getContentPane().add(panel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new SolveButton(solver, puzzle, panel));
        frame.setJMenuBar(menuBar);

        new MouseTracker(puzzle, panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setLocationRelativeTo (null); // Center on screen.
        frame.setVisible(true);
    }

    private static Nonogram createPuzzle() {
        //create widthField:
        JTextField nRowsField = new JTextField(3);
        nRowsField.setToolTipText("Enter the number of rows");
        //create HeightField:
        JTextField nColumnsField = new JTextField(3);
        nColumnsField.setToolTipText("Enter the number of columns");
        //create panel:
        JPanel dimensionsPanel = new JPanel();
        dimensionsPanel.add(new JLabel("Rows:"));
        dimensionsPanel.add(nRowsField);
        dimensionsPanel.add(Box.createHorizontalStrut(15));
        dimensionsPanel.add(new JLabel("Columns:"));
        dimensionsPanel.add(nColumnsField);
        //Keep asking for input until valid input has been entered or the user pressed cancel:
        int nRows;
        int nColumns;
        while (true) {
            int chosenOption = JOptionPane.showConfirmDialog(null, dimensionsPanel, "Enter dimensions",
                    JOptionPane.OK_CANCEL_OPTION);
            if (chosenOption == JOptionPane.CANCEL_OPTION) {
                return null;
            }
            try {
                nRows = Integer.parseInt(nRowsField.getText());
                nColumns = Integer.parseInt(nColumnsField.getText());
            } catch (NumberFormatException exception) {
                nRows = 0;
                nColumns = 0;
            }
            //check if dimensions are valid, if not show an error message, else break out if the loop:
            if (nRows < 3 || nRows > 25 || nColumns < 3 || nColumns > 25) {
                chosenOption = JOptionPane.showConfirmDialog(null,
                        "These dimensions are invalid, please choose again.", "Invalid input",
                        JOptionPane.OK_CANCEL_OPTION);
                if (chosenOption == JOptionPane.CANCEL_OPTION) {
                    return null;
                }
            } else {
                break;
            }
        }
        return new Nonogram(nRows, nColumns);
    }

    public static void main( String[] args ) {
        Nonogram puzzle = createPuzzle();

        if (puzzle != null) {
            openNonogramSolverWindow(puzzle);
        }
    }
}
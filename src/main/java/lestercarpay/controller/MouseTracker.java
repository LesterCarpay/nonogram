package lestercarpay.controller;

import lestercarpay.model.Nonogram;
import lestercarpay.model.Specification;
import lestercarpay.view.NonogramPanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseTracker extends MouseInputAdapter {

    Nonogram puzzle;
    NonogramPanel panel;

    public MouseTracker(Nonogram puzzle, NonogramPanel panel) {
        setPuzzle(puzzle);
        setPanel(panel);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    public void setPuzzle(Nonogram puzzle) {
        this.puzzle = puzzle;
    }

    public void setPanel(NonogramPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (onRowSpecificationField(e.getPoint())) {
            int row = whichRow(e.getPoint());
            String newSpecification = getSpecificationInputFromUser(puzzle.getRowSpecification(row));
            try {
                puzzle.setRowSpecification(row, new Specification(newSpecification));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(panel, "Sorry, this is not a valid specification.");
            }
        }
        if (onColumnSpecificationField(e.getPoint())) {
            int column = whichColumn(e.getPoint());
            String newSpecification = getSpecificationInputFromUser(puzzle.getRowSpecification(column));
            try {
                puzzle.setColumnSpecification(column, new Specification(newSpecification));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(panel, "Sorry, this is not a valid specification.");
            }
        }
    }

    private boolean onRowSpecificationField(Point point) {
        int xLower = panel.getRowSpecificationFieldX();
        int xUpper = xLower + panel.getRowSpecificationFieldWidth();
        int yLower = panel.getPuzzleFieldY();
        int yUpper = yLower + panel.getPuzzleFieldHeight();
        return point.getX() > xLower && point.getX() < xUpper && point.getY() > yLower && point.getY() < yUpper;
    }

    private int whichRow(Point point) {
       return ((int)(point.getY() - panel.getPuzzleFieldY()))/panel.getCellHeight();
    }


    private boolean onColumnSpecificationField(Point point) {
        int xLower = panel.getPuzzleFieldX();
        int xUpper = xLower + panel.getPuzzleFieldWidth();
        int yLower = panel.getColumnSpecificationFieldY();
        int yUpper = yLower + panel.getColumnSpecificationFieldHeight();
        return point.getX() > xLower && point.getX() < xUpper && point.getY() > yLower && point.getY() < yUpper;
    }

    private int whichColumn(Point point) {
        return ((int)(point.getX() - panel.getPuzzleFieldX()))/panel.getCellWidth();
    }

    private String getSpecificationInputFromUser(Specification oldSpecification) {
        return (String) JOptionPane.showInputDialog(panel, "Please enter the specification:",
                "Set Specification", JOptionPane.QUESTION_MESSAGE, null, null,
                oldSpecification);
    }
}

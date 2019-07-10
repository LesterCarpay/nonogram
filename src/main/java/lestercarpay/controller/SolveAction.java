package lestercarpay.controller;

import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;
import lestercarpay.view.NonogramPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class SolveAction extends AbstractAction implements Observer {

    private Solver solver;
    private NonogramPanel panel;

    SolveAction (Solver solver, Nonogram puzzle, NonogramPanel panel) {
        super("Solve");
        this.solver = solver;
        this.panel = panel;
        setEnabled(shouldBeEnabled());
        puzzle.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            solver.solve();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(panel, "Could not solve puzzle");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setEnabled(shouldBeEnabled());
    }

    private boolean shouldBeEnabled() {
        return !solver.isSolved();
    }
}

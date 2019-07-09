package lestercarpay.controller;

import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class SolveAction extends AbstractAction implements Observer {

    private Solver solver;

    public SolveAction (Solver solver, Nonogram puzzle) {
        super("Solve");
        this.solver = solver;
        setEnabled(shouldBeEnabled());
        puzzle.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        solver.solve();
    }

    @Override
    public void update(Observable o, Object arg) {
        setEnabled(shouldBeEnabled());
    }

    private boolean shouldBeEnabled() {
        return !solver.isSolved();
    }
}

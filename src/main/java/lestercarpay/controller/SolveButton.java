package lestercarpay.controller;

import lestercarpay.model.Nonogram;
import lestercarpay.model.Solver;
import lestercarpay.view.NonogramPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SolveButton extends JButton {

    public SolveButton(Solver solver, Nonogram puzzle, NonogramPanel panel) {
        super(new SolveAction(solver, puzzle, panel));
        setBackground(Color.LIGHT_GRAY);
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
        setToolTipText("Solve the puzzle");
        setFont(new Font("LucidaGrande", Font.PLAIN, 12));
    }
}

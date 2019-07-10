package lestercarpay.view;

import lestercarpay.model.Cell;
import lestercarpay.model.Nonogram;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NonogramPanel extends JPanel implements Observer {

    private Nonogram puzzle;

    private static final String FONT_NAME = "Ariel";
    private static final int FONT_STYLE = Font.PLAIN;

    public NonogramPanel (Nonogram puzzle) {
        this.puzzle = puzzle;
        puzzle.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setDefaultFontSize(g);
        super.paintComponent(g);
        paintPuzzleOutline(g);
        paintPuzzle(g);
        setDefaultFontSize(g);
        paintRowSpecifications(g);
        paintColumnSpecifications(g);
    }

    private void paintPuzzleOutline(Graphics g) {
        int width = getPuzzleFieldWidth();
        int height = getPuzzleFieldHeight();
        int x = getPuzzleFieldX();
        int y = getPuzzleFieldY();

        g.drawRect(x, y, width, height);
    }

    private void paintPuzzle(Graphics g) {
        for (int row = 0; row < puzzle.getNRows(); row++) {
            for (int column = 0; column < puzzle.getNColumns(); column++) {
                paintCellIfFilled(g, row, column);
            }
        }
    }

    private void paintRowSpecifications(Graphics g) {
        for (int row = 0; row < puzzle.getNRows(); row++) {
            paintRowSpecification(g, row);
        }
    }

    private void paintColumnSpecifications(Graphics g) {
        for (int column = 0; column < puzzle.getNColumns(); column++) {
            paintColumnSpecification(g, column);
        }
    }

    private void paintCellIfFilled(Graphics g, int row, int column) {
        if (puzzle.getCell(row, column) == Cell.FILLED) {
            paintCell(g, row, column);
        }
    }

    private void paintCell(Graphics g, int row, int column) {
        int x = getCellX(column);
        int y = getCellY(row);
        int width = getCellWidth();
        int height = getCellHeight();
        g.fillRect(x, y, width, height);
    }

    private void paintRowSpecification(Graphics g, int row) {
        int x = getRowSpecificationX(g, row);
        int y = getRowSpecificationY(g, row);
        String specificationString = puzzle.getRowSpecification(row).toString();
        setAdjustedFontSizeRowSpecification(g, row);
        g.drawString(specificationString, x, y);
        setDefaultFontSize(g);
    }

    private void paintColumnSpecification(Graphics g, int column) {
        List<Integer> blocks = puzzle.getColumnSpecification(column).getBlocks();
        int y = getColumnSpecificationFieldY() + getColumnSpecificationFieldHeight();
        setAdjustedFontSizeColumnSpecification(g, column);
        for (int blockIndex = blocks.size() - 1; blockIndex >= 0; blockIndex--) {
            int block = blocks.get(blockIndex);
            int x = getColumnSpecificationBlockX(g, column, block);
            g.drawString(Integer.toString(block), x, y);
            y -= g.getFontMetrics().getHeight();
        }
        setDefaultFontSize(g);
    }

    private void setDefaultFontSize(Graphics g) {
        int maxHeight = (getCellHeight()*8)/10;
        int size = 2;
        Font font = new Font(FONT_NAME, FONT_STYLE, size);
        while (g.getFontMetrics(font).getHeight() < maxHeight) {
            size++;
            font = new Font(FONT_NAME, FONT_STYLE, size);
        }
        size--;
        g.setFont(new Font(FONT_NAME, FONT_STYLE, size));
    }

    private void setAdjustedFontSizeRowSpecification(Graphics g, int row) {
        String stringSpecification = puzzle.getRowSpecification(row).toString();
        int maxWidth = getRowSpecificationFieldWidth();
        int fontSize = g.getFont().getSize();
        while(g.getFontMetrics().stringWidth(stringSpecification) > maxWidth) {
            fontSize--;
            g.setFont(new Font(FONT_NAME, FONT_STYLE, fontSize));
        }
    }

    private void setAdjustedFontSizeColumnSpecification(Graphics g, int column) {
        List<Integer> blocks = puzzle.getColumnSpecification(column).getBlocks();
        int maxHeight = getColumnSpecificationFieldHeight();
        int fontSize = g.getFont().getSize();
        int height = blocks.size()*g.getFontMetrics().getHeight();
        while (height > maxHeight) {
            fontSize--;
            g.setFont(new Font(FONT_NAME, FONT_STYLE, fontSize));
            height = blocks.size()*g.getFontMetrics().getHeight();
        }
    }

    private int getPuzzleFieldWidth() {
        return (getWidth()*2)/3;
    }

    private int getPuzzleFieldHeight() {
        return (getHeight()*2)/3;
    }

    private int getPuzzleFieldX() {
        return ((getWidth() - getPuzzleFieldWidth())*3)/4;
    }

    private int getPuzzleFieldY() {
        return ((getHeight() - getPuzzleFieldHeight())*3)/4;
    }

    private int getCellWidth() {
        return getPuzzleFieldWidth()/puzzle.getNColumns();
    }

    private int getCellHeight() {
        return getPuzzleFieldHeight()/puzzle.getNRows();
    }

    private int getCellX(int column) {
        return getPuzzleFieldX() + column*getCellWidth();
    }

    private int getCellY(int row) {
        return getPuzzleFieldY() + row*getCellHeight();
    }

    private int getRowSpecificationFieldWidth() {
        return (getPuzzleFieldX()*8)/10;
    }

    private int getRowSpecificationFieldX() {
        return getPuzzleFieldX()/10;
    }

    private int getRowSpecificationX(Graphics g, int row) {
        String specificationString = puzzle.getRowSpecification(row).toString();
        int width = g.getFontMetrics().stringWidth(specificationString);
        return getRowSpecificationFieldX() + getRowSpecificationFieldWidth() - width;
    }

    private int getRowSpecificationY(Graphics g, int row) {
        return getCellY(row) + getCellHeight()/2 + g.getFontMetrics().getHeight()/2;
    }

    private int getColumnSpecificationFieldHeight() {
        return (getPuzzleFieldY()*8)/10;
    }

    private int getColumnSpecificationFieldY() {
        return getPuzzleFieldY()/10;
    }

    private int getColumnSpecificationBlockX(Graphics g, int column, int block) {
        int width = g.getFontMetrics().stringWidth(Integer.toString(block));
        return getCellX(column) + getCellWidth()/2 - width/2;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

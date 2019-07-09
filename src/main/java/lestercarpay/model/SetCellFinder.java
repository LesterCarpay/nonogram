package lestercarpay.model;

public class SetCellFinder {

    private Cell[] section;
    private Specification specification;
    private int sectionLength;
    private int[] margins;

    public SetCellFinder(Cell[] section, Specification specification) {
        this.section = section;
        this.specification = specification;
        this.sectionLength = section.length;
        initialiseMargins();
        assert getLeeway() >= 0;
    }

    private void initialiseMargins() {
        int size = specification.getBlocks().size();
        this.margins = new int[size];
        margins[0] = 0;
        for (int i = 1; i < size; i++) {
            margins[i] = 1;
        }
    }

    public Cell[] findSetCells() {
        Cell[] newSetCells = findNewSetCells();
        assert newSetCells != null;
        return addNewSetCells(newSetCells);
    }

    private Cell[] addNewSetCells(Cell[] newSetCells) {
        Cell[] result = new Cell[sectionLength];
        for (int i = 0; i < sectionLength; i++) {
            result[i] = section[i] == Cell.EMPTY ? newSetCells[i] : section[i];
        }
        return result;
    }

    private Cell[] findNewSetCells() {
        return findNewSetCells(getLeeway(), 0);
    }

    private Cell[] findNewSetCells(int leeway, int marginIndex) {
        if (marginIndex == margins.length || leeway == 0) {
            return makeSectionIfNoConflict();
        }
        Cell[] result = null;
        for (int marginIncrease = 0; marginIncrease <= leeway; marginIncrease++) {
            margins[marginIndex] += marginIncrease;
            result = combineSections(result, findNewSetCells(leeway - marginIncrease, marginIndex + 1));
            margins[marginIndex] -= marginIncrease;
        }
        return result;
    }

    private Cell[] combineSections(Cell[] section1, Cell[] section2) {
        if (section1 == null && section2 == null) {
            return null;
        }
        if (section2 == null) {
            return section1;
        }
        if (section1 == null) {
            return section2;
        }
        Cell[] result = new Cell[sectionLength];
        for (int i = 0; i < sectionLength; i++) {
            result[i] = combineCells(section1[i], section2[i]);
        }
        return result;
    }

    private Cell combineCells(Cell cell1, Cell cell2) {
        if (cell1 == cell2) {
            return cell1;
        }
        return Cell.EMPTY;
    }

    private int getLeeway() {
        return sectionLength - specification.getMinimumLength();
    }

    private Cell[] makeSectionIfNoConflict() {
        Cell[] candidateSection = makeSection();
        if (conflictBetweenSections(section, candidateSection)) {
            return null;
        }
        return candidateSection;
    }

    private static boolean conflictBetweenSections(Cell[] certainSection, Cell[] candidateSection) {
        for (int i = 0; i < certainSection.length; i++) {
            if (conflictBetweenCells(certainSection[i], candidateSection[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean conflictBetweenCells(Cell certainCell, Cell candidateCell) {
        return ((certainCell == Cell.FILLED && candidateCell != Cell.FILLED)
        ||
                (certainCell == Cell.CROSSED && candidateCell == Cell.FILLED));
    }

    private Cell[] makeSection() {
        Cell[] result = new Cell[sectionLength];
        int i = 0;
        for (int blockIndex = 0; blockIndex < margins.length; blockIndex++) {
            for (int j = 0; j < margins[blockIndex]; j++, i++) {
                result[i] = Cell.CROSSED;
            }
            for (int j = 0; j < specification.getBlocks().get(blockIndex); j++, i++) {
                result[i] = Cell.FILLED;
            }
        }
        for (;i < sectionLength; i++) {
            result[i] = Cell.CROSSED;
        }
//        System.out.print("Margins:");
//        for (int margin : margins) {
//            System.out.print(margin);
//        }
//        System.out.println();
//        System.out.print("Section:");
//        for (Cell cell : result) {
//            System.out.print(cell == Cell.FILLED ? 'X' : 'O');
//        }
//        System.out.println();
        return result;
    }
}

package lestercarpay.model;

import java.util.ArrayList;
import java.util.List;

public class Specification {
    private List<Integer> blocks;

    public Specification(int... blocks) {
        this.blocks = new ArrayList<>();
        for (int block : blocks) {
            this.blocks.add(block);
        }
    }

    Specification() {
        this(0);
    }

    public List<Integer> getBlocks() {
        return blocks;
    }

    public int getMinimumLength() {
        int minimumLength = 0;
        for (int blocks : blocks) {
            minimumLength += blocks;
            minimumLength += 1;
        }
        minimumLength -= 1;
        return minimumLength;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int block : getBlocks()) {
            result.append(block);
            result.append(' ');
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String toVerticalString() {
        return toString().replace(' ', '\n');
    }
}

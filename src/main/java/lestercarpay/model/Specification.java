package lestercarpay.model;

import java.util.ArrayList;
import java.util.List;

public class Specification {
    private List<Integer> blocks;

    Specification(int... blocks) {
        this.blocks = new ArrayList<>();
        for (int block : blocks) {
            this.blocks.add(block);
        }
    }

    Specification() {
        this(0);
    }
}

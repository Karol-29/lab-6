package domain;

import java.awt.Color;

public class SleepyCell extends Cell {
    private int sleepCounter;
    private static final int SLEEP_THRESHOLD = 3;

    public SleepyCell(AManufacturing am, int row, int column) {
        super(am, row, column, true);
        this.sleepCounter = 0;
        this.color = Color.BLUE;
    }

    @Override
    public void decide() {
        sleepCounter++;
        if (sleepCounter >= SLEEP_THRESHOLD) {
            nextState = isActive() ? Artefact.INACTIVE : Artefact.ACTIVE;
            sleepCounter = 0;
        } else {
            nextState = state;
        }
    }

    @Override
    public Color getColor() {
        return isActive() ? Color.BLUE : Color.CYAN;
    }
}
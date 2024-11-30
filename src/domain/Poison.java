package domain;

import java.awt.Color;
import java.io.Serializable;

public class Poison extends Artefact implements Thing, Serializable{
    private static final Color[] RAINBOW_COLORS = {
        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE
    };
    private int colorIndex;

    public Poison(AManufacturing am, int row, int column) {
        super();
        this.state = ACTIVE;
        this.colorIndex = (int) (Math.random() * RAINBOW_COLORS.length);
        am.setThing(row, column, this);
    }

    @Override
    public void decide() {
        // El veneno siempre está activo, no necesita tomar decisiones
    }

    @Override
    public void change() {
        colorIndex = (colorIndex + 1) % RAINBOW_COLORS.length;
    }

    @Override
    public Color getColor() {
        return RAINBOW_COLORS[colorIndex];
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int shape() {
        return Thing.SQUARE;
    }
}
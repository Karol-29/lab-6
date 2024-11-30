package domain;
import java.io.Serializable;

import java.awt.Color;

public class Reflector implements Thing, Serializable {
    private AManufacturing aManufacturing;
    private int row, column;
    private boolean reflecting;
    private Color color;

    /** Crea un reflector en una posición de la rejilla de fabricación
     * @param am Referencia al sistema AManufacturing
     * @param row Fila donde se crea el reflector
     * @param column Columna donde se crea el reflector
     */
    public Reflector(AManufacturing am, int row, int column) {
        this.aManufacturing = am;
        this.row = row;
        this.column = column;
        this.reflecting = true;  // El reflector comienza activo
        this.color = Color.blue;  // Color distintivo
        am.setThing(row, column, this);  // Se añade al lattice
    }

    /** Decide el siguiente estado del reflector basado en las células vecinas */
    @Override
    public void decide() {
        // Verifica cuántos vecinos están activos
        int activeNeighbors = aManufacturing.neighborsActive(row, column);
        if (activeNeighbors > 0) {
            reflecting = true;  // Si hay células activas alrededor, el reflector sigue activo
        } else {
            reflecting = false;  // Si no hay células activas, el reflector se desactiva
        }
    }

    /** Cambia el estado del reflector */
    @Override
    public void change() {
        // El reflector simplemente cambia su color dependiendo de si está activo
        color = reflecting ? Color.blue : Color.gray;
    }

    /** Devuelve el color del reflector dependiendo de su estado */
    @Override
    public Color getColor() {
        return color;
    }

    /** Indica si el reflector está activo */
    @Override
    public boolean isActive() {
        return reflecting;
    }
}

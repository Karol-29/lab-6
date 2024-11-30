package domain;

import java.awt.Color;
import java.io.Serializable;

public class TouristCell extends Cell implements Serializable {
    private int centerRow;
    private int centerColumn;

    public TouristCell(AManufacturing am, int row, int column) {
        super(am, row, column, true); // Inician activas
        this.color = Color.orange; // Activas son de color naranja
        this.centerRow = am.getSize() / 2;
        this.centerColumn = am.getSize() / 2;
    }

    @Override
    public void decide() {
        if (isActive()) {
            // Si está activa, intenta moverse hacia el centro
            if (!moveTowards(centerRow, centerColumn)) {
                nextState = Artefact.INACTIVE; // Cambia a inactivo si no puede moverse
            }
        } else {
            // Si está inactiva, intenta moverse hacia los bordes
            if (!moveTowards(getEdgeRow(), getEdgeColumn())) {
                nextState = Artefact.ACTIVE; // Cambia a activo si no puede moverse
            }
        }
    }

    @Override
    public void change() {
        super.change();
        // Cambiar color dependiendo del estado
        this.color = (isActive() ? Color.orange : Color.yellow);
    }

    // Mover la célula en la dirección hacia una fila y columna objetivo
    private boolean moveTowards(int targetRow, int targetColumn) {
        int dr = (targetRow > row) ? 1 : (targetRow < row) ? -1 : 0;
        int dc = (targetColumn > column) ? 1 : (targetColumn < column) ? -1 : 0;

        if (aManufactuing.isEmpty(row + dr, column + dc)) {
            aManufactuing.setThing(row + dr, column + dc, this);
            aManufactuing.setThing(row, column, null); // Vaciar la posición anterior
            this.row += dr;
            this.column += dc;
            return true; // Movimiento exitoso
        }
        return false; // No pudo moverse
    }

    // Obtener fila hacia los bordes
    private int getEdgeRow() {
        return (row < centerRow) ? 0 : aManufactuing.getSize() - 1;
    }

    // Obtener columna hacia los bordes
    private int getEdgeColumn() {
        return (column < centerColumn) ? 0 : aManufactuing.getSize() - 1;
    }
}

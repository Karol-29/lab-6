package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;

public class SleepyCellTest {
    
    @Test
    public void testSleepyCellCreation() {
        AManufacturing am = new AManufacturing();
        SleepyCell cell = new SleepyCell(am, 5, 5);
        assertNotNull(cell);
        assertEquals(Color.BLUE, cell.getColor());
        assertTrue(cell.isActive());
    }

    @Test
    public void testSleepyCellBehavior() {
        AManufacturing am = new AManufacturing();
        SleepyCell cell = new SleepyCell(am, 5, 5);
        
        assertTrue(cell.isActive());
        
        // Primer tic-tac
        cell.decide();
        cell.change();
        assertTrue(cell.isActive());
        
        // Segundo tic-tac
        cell.decide();
        cell.change();
        assertTrue(cell.isActive());
        
        // Tercer tic-tac (debería cambiar)
        cell.decide();
        cell.change();
        assertFalse(cell.isActive());
    }
}
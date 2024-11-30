package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;

public class ReflectorTest {
    private AManufacturing am;
    private Reflector reflector;

    @Before
    public void setUp() {
        am = new AManufacturing();  // Inicializa el entorno de prueba
        reflector = new Reflector(am, 10, 10);  // Crea un reflector en la posición (10, 10)
    }
    
    @Test
    public void testReflectorActivation() {
        AManufacturing am = new AManufacturing();
        Reflector reflector = new Reflector(am, 10, 10);
        
        assertTrue(reflector.isActive());  // El reflector comienza activo
        
        // Agregar una célula activa cerca del reflector
        Cell cell = new Cell(am, 9, 9, true);
        
        reflector.decide();
        assertTrue(reflector.isActive());  // El reflector sigue activo por la célula activa
    }

    @Test
    public void testReflectorColorChange() {
        AManufacturing am = new AManufacturing();
        Reflector reflector = new Reflector(am, 10, 10);
        
        assertEquals(Color.blue, reflector.getColor());  // Inicialmente, debería estar azul
        
        // Agregar una célula activa cerca del reflector
        Cell cell = new Cell(am, 9, 9, true);
        
        reflector.decide();
        reflector.change();
        assertEquals(Color.blue, reflector.getColor());  // El reflector sigue azul
    }
}
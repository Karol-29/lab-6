package domain;
import java.util.*;
import  java.lang.Class;
import java.lang.reflect.Constructor;
import java.io.File;  // Asegúrate de agregar esta línea
import java.io.*;
/*No olviden adicionar la documentacion*/
public class AManufacturing implements Serializable{
    static private int SIZE=50;
    private Thing[][] lattice;
    
    public AManufacturing() {
        lattice=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                lattice[r][c]=null;
            }
        }
        someThings();
    }

    public int  getSize(){
        return SIZE;
    }

    public Thing getThing(int r,int c){
        return lattice[r][c];
    }

    public void setThing(int r, int c, Thing e){
        lattice[r][c]=e;
    }

    public void someThings() {
        Cell yersinia = new Cell(this, 10, 10, true);
        Cell listeria = new Cell(this, 15, 15, true);
        TouristCell move = new TouristCell(this, 0, 0);
        TouristCell walk = new TouristCell(this, 5, 5);
        Poison mercury = new Poison(this, 0, 0);
        Poison arsenic = new Poison(this, 0, SIZE - 1);
        SleepyCell Juan = new SleepyCell(this, 20, 20);
        SleepyCell Karol = new SleepyCell(this, 25, 25);
        Reflector David = new Reflector(this, 4, 4);
        Reflector Estefany = new Reflector(this, 35, 35);
    }
    
    public int neighborsActive(int r, int c){
        int num=0;
        for(int dr=-1; dr<2;dr++){
            for (int dc=-1; dc<2;dc++){
                if ((dr!=0 || dc!=0) && inLatice(r+dr,c+dc) && 
                    (lattice[r+dr][c+dc]!=null) &&  (lattice[r+dr][c+dc].isActive())) num++;
            }
        }
        return (inLatice(r,c) ? num : 0);
    }
   

    
    public boolean isEmpty(int r, int c){
        return (inLatice(r,c) && lattice[r][c]==null);
    }    
        
    private boolean inLatice(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
   
    public void ticTac() {
        // Paso 1: Cada célula decide su próximo estado basado en su propio estado y el de sus vecinas
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Thing thing = lattice[r][c];
                if (thing != null) {
                    thing.decide();
                }
            }
        }
        
        // Paso 2: Todas las células aplican el cambio de estado
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Thing thing = lattice[r][c];
                if (thing != null) {
                    thing.change();
                }
            }
        }
    }
    public void save(File file) throws ReplicateException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);  // Guardamos el objeto completo
        } catch (IOException e) {
            throw new ReplicateException("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static AManufacturing open(File file) throws ReplicateException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (AManufacturing) ois.readObject();  // Deserializamos el objeto
        } catch (IOException | ClassNotFoundException e) {
            throw new ReplicateException("Error al abrir el archivo: " + e.getMessage());
        }
    }


    public static AManufacturing open00(File file) throws ReplicateException{
        throw new ReplicateException("Open "+ReplicateException.EN_CONSTRUCION+" "+file.getName());
    }
    public void save00(File file) throws ReplicateException{
        throw new ReplicateException("Save "+ReplicateException.EN_CONSTRUCION+" "+file.getName());
    }
    public void export00(File file) throws ReplicateException{
        throw new ReplicateException("Export "+ReplicateException.EN_CONSTRUCION+" "+file.getName());
    }
    public void export(File file) throws ReplicateException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Itera sobre todas las celdas y guarda el estado de cada cosa
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    Thing thing = lattice[r][c];
                    if (thing != null) {
                        // Escribe el tipo de cosa y su posición en el archivo
                        writer.write(thing.getClass().getSimpleName() + " " + r + " " + c);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new ReplicateException("Error al exportar el archivo: " + e.getMessage());
        }
    }

    public static AManufacturing importe00(File file) throws ReplicateException{
        throw new ReplicateException("Import "+ReplicateException.EN_CONSTRUCION+" "+file.getName());
    }
    public void importe(File file) throws ReplicateException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Limpiar espacios innecesarios
                line = line.trim();
                if (!line.isEmpty()) {
                    // Separar la línea por espacios
                    String[] parts = line.split(" ");
                    String type = parts[0];
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);

                    // Crear el objeto correspondiente basándonos en el tipo
                    Thing thing = createThingByType(type);
                    if (thing != null) {
                        setThing(row, col, thing);  // Coloca la cosa en la posición indicada
                    }
                }
            }
        } catch (IOException e) {
            throw new ReplicateException("Error al importar el archivo: " + e.getMessage());
        }
    }

    private Thing createThingByType(String type) {
        switch (type) {
            case "Cell":
                return new Cell(this, 0, 0, true);  // Crea un objeto Cell
            case "Poison":
                return new Poison(this, 0, 0);  // Crea un objeto Poison
            case "Reflector":
                return new Reflector(this, 0, 0);  // Crea un objeto Reflector
            // Añadir más casos según sea necesario
            default:
                return null;  // Si el tipo no es reconocido
        }
    }

    public static AManufacturing nuevo() {
        AManufacturing newAManufacturing = new AManufacturing();  // Crear una nueva instancia de AManufacturing
        return newAManufacturing;  // Retornar la nueva instancia
    }


}

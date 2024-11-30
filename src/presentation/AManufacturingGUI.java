package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;


public class AManufacturingGUI extends JFrame {
    public static final int SIDE = 11;
    public final int SIZE;
    private JButton ticTacButton;
    private JPanel controlPanel;
    private PhotoAManufacturing photo;
    private AManufacturing aManufacturing;
    private JMenuItem newItem, openItem, saveItem, exitItem, exportItem, importItem;
    private JMenuBar menuBar;
    private JMenu menuArchivo;

    public AManufacturingGUI() {
        aManufacturing = new AManufacturing();
        SIZE = aManufacturing.getSize();
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("aManufacturing celular");
        photo = new PhotoAManufacturing(this);
        ticTacButton = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(ticTacButton, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE + 15, SIDE * SIZE + 72));
        setResizable(false);
        photo.repaint();
        prepareElementsMenu();
    }

    private void prepareElementsMenu() {
        // Barra de menú
        menuBar = new JMenuBar();
        menuArchivo = new JMenu("Archivo");

        // Opciones del menú
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        importItem = new JMenuItem("Import");
        exportItem = new JMenuItem("Export");
        exitItem = new JMenuItem("Salir");

        // Añadir las opciones al menú "Archivo"
        menuArchivo.add(newItem);
        menuArchivo.addSeparator();
        menuArchivo.add(openItem);
        menuArchivo.addSeparator();
        menuArchivo.add(saveItem);
        menuArchivo.addSeparator();
        menuArchivo.add(importItem);
        menuArchivo.addSeparator();
        menuArchivo.add(exportItem);
        menuArchivo.addSeparator();
        menuArchivo.add(exitItem);

        // Añadir el menú a la barra de menú
        menuBar.add(menuArchivo);

        // Establecer la barra de menú en la ventana
        setJMenuBar(menuBar);
    }

    private void prepareActions() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Acción del botón Tic-Tac
        ticTacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticTacButtonAction();
            }
        });
        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();  // Llamada al método para "Save"
            }
        });
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();  // Llamada al método para "Save"
            }
        });
        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importFile();  // Llamada al método para "Save"
            }
        });
        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportFile();  // Llamada al método para "Save"
            }
        });
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFile();  // Llamada al método para "Save"
            }
        });
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();  // Llamada al método para "Save"
            }
        });

    }
    private void exit(){
        System.exit(0);
    }
    private void newFile(){
        aManufacturing=AManufacturing.nuevo();
        photo.repaint();
    }
    private void exportFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Llama al método export para guardar el estado de AManufacturing en un archivo de texto
                aManufacturing.export(selectedFile);
            } catch (ReplicateException e) {
                // Si ocurre un error, muestra el mensaje de error con el mensaje de la excepción
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),  // Mensaje de la excepción
                        "Error al exportar", // Título del cuadro de diálogo
                        JOptionPane.ERROR_MESSAGE // Tipo de mensaje (puede ser otro)
                );
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo.");
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                aManufacturing = AManufacturing.open(selectedFile);
                photo.repaint();

            } catch (ReplicateException e) {
                JOptionPane.showMessageDialog(
                        this,                       
                        e.getMessage(),
                        "Error al abrir",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo.");
        }
    }

    private void importFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {

                aManufacturing.importe(selectedFile);

                photo.repaint();
            } catch (ReplicateException e) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Error al importar", 
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo.");
        }
    }


    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                aManufacturing.save(selectedFile);  // Suponiendo que el método save lanza una excepción
            } catch (ReplicateException e) {
                // Mostrar el mensaje de error con el mensaje de la excepción
                JOptionPane.showMessageDialog(
                        this,                         // Componente principal
                        e.getMessage(),               // Obtener el mensaje de la excepción
                        "Error al guardar",           // Título del cuadro de diálogo
                        JOptionPane.ERROR_MESSAGE     // Tipo de mensaje (puede ser otro)
                );
            }

        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún archivo.");
        }
    }

    private void ticTacButtonAction() {
        aManufacturing.ticTac();
        photo.repaint();
    }

    public AManufacturing getaManufacturing() {
        return aManufacturing;
    }

    public static void main(String[] args) {
        AManufacturingGUI ca = new AManufacturingGUI();
        ca.setVisible(true);
    }
}


class PhotoAManufacturing extends JPanel {
    private AManufacturingGUI gui;

    public PhotoAManufacturing(AManufacturingGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE + 10, gui.SIDE * gui.SIZE + 10));
    }

    public void paintComponent(Graphics g) {
        AManufacturing aManufacturing = gui.getaManufacturing();
        super.paintComponent(g);

        for (int c = 0; c <= aManufacturing.getSize(); c++) {
            g.drawLine(c * gui.SIDE, 0, c * gui.SIDE, aManufacturing.getSize() * gui.SIDE);
        }
        for (int f = 0; f <= aManufacturing.getSize(); f++) {
            g.drawLine(0, f * gui.SIDE, aManufacturing.getSize() * gui.SIDE, f * gui.SIDE);
        }
        for (int f = 0; f < aManufacturing.getSize(); f++) {
            for (int c = 0; c < aManufacturing.getSize(); c++) {
                if (aManufacturing.getThing(f, c) != null) {
                    g.setColor(aManufacturing.getThing(f, c).getColor());
                    if (aManufacturing.getThing(f, c).shape() == Thing.SQUARE) {
                        if (aManufacturing.getThing(f, c).isActive()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        } else {
                            g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        if (aManufacturing.getThing(f, c).isActive()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        } else {
                            g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                }
            }
        }
    }
}

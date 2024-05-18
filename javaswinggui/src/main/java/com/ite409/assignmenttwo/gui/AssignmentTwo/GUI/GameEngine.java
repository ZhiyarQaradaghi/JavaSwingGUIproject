package com.ite409.assignmenttwo.gui.AssignmentTwo.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Database.DatabaseOperations;
import com.ite409.assignmenttwo.gui.AssignmentTwo.FileIO.FileOperations;
import com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JFrame {
    public List<Shape> shapes;
    private final String title = "AUIS - Shape Game Engine";
    private final DefaultTableModel model;
    private JTable table;
    private boolean isEditingEnabled = false;
    DatabaseOperations db = new DatabaseOperations();
    FileOperations fo = new FileOperations();

    public GameEngine() {
        db = new DatabaseOperations();
        db.createShapesTable();
        shapes = new ArrayList<>();
        model = new DefaultTableModel(new String[] { "Shape Type", "ID", "Color", "Size", "X", "Y", "Z" }, 0);
        init();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(GameEngine.this,
                "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        model.addRow(new Object[] { shape.getClass().getSimpleName(), shape.getId(), shape.getColor(),
                shape.calculateSize(), shape.getX(), shape.getY(), shape.getZ(), shape.getSide() });

        // just for checking
        System.out.println("Shape " + shape.getClass().getSimpleName() + " added !");
        System.out.println("Info : ");
        System.out.println("ID: " + shape.getId());
        System.out.println("Color: " + shape.getColor());
        System.out.println("Size: " + shape.calculateSize());
        System.out.println("X: " + shape.getX());
        System.out.println("Y: " + shape.getY());
        System.out.println("Z: " + shape.getZ());
        System.out.println("Side: " + shape.getSide());
    }

    private void init() {
        // reference - Chatgpt q: What java swing class can I use for a menu bar?
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showOptionDialog(null, "Choose where to save the data:", "Save Data",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new String[] { "File", "Database" }, null);
                if (option == JOptionPane.YES_OPTION) {
                    GameEngine gameEngine = new GameEngine();
                    fo.saveToFile(shapes, gameEngine);
                } else if (option == JOptionPane.NO_OPTION) {
                    db.saveToDatabase(shapes);
                }
            }
        });
        fileMenu.add(saveMenuItem);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to combine data from file and database?",
                        "Combine Data", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    fo.loadFromFile(shapes, model, GameEngine.this);
                    db.loadFromDatabase(shapes, model);
                } else if (option == JOptionPane.NO_OPTION) {
                    int option2 = JOptionPane.showOptionDialog(null, "Choose where to load the data:", "Load Data",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[] { "File", "Database" }, null);
                    if (option2 == JOptionPane.YES_OPTION) {
                        fo.loadFromFile(shapes, model, GameEngine.this);
                    } else if (option2 == JOptionPane.NO_OPTION) {
                        db.loadFromDatabase(shapes, model);
                    }
                }
            }
        });
        fileMenu.add(loadMenuItem);

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditingEnabled;
            }
        };

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEditingEnabled = !isEditingEnabled;
                updateEditability();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    shapes.remove(selectedRow);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        this.setTitle(title);
        this.add(buttonPanel, "South");
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setBounds(200, 100, 800, 700);
        this.setVisible(true);
    }

    private void updateEditability() {
        table.getTableHeader().setReorderingAllowed(!isEditingEnabled);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (isEditingEnabled) {
                table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
            } else {
                table.setDefaultEditor(Object.class, null); // just to disable any editing
            }
        }
    }

}

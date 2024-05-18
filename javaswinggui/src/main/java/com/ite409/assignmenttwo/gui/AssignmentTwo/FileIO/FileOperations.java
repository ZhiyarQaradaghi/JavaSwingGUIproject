package com.ite409.assignmenttwo.gui.AssignmentTwo.FileIO;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes.*;
import com.ite409.assignmenttwo.gui.AssignmentTwo.GUI.GameEngine;

public class FileOperations {

    public void saveToFile(List<Shape> shapes, GameEngine gameEngine) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(gameEngine);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                for (Shape shape : shapes) {
                    // Modified the format to encapsulate color value in quotes
                    String shapeDetails = String.format("%s,\"%s\",%.2f,%.2f,%.2f,%.2f%n",
                            shape.getClass().getSimpleName(), shape.getColor(), shape.getX(), shape.getY(),
                            shape.getZ(), shape.getSide());
                    bos.write(shapeDetails.getBytes());
                    bos.flush();
                }
                JOptionPane.showMessageDialog(null, "Data saved to file successfully.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving data to file.");
            }
        }
    }

    public void loadFromFile(List<Shape> shapes, DefaultTableModel model, GameEngine gameEngine) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(gameEngine);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                shapes.clear();
                model.setRowCount(0);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Read line from file: " + line);
                    String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    if (tokens.length < 6) {
                        System.err.println("Invalid line format: " + line);
                        continue;
                    }
                    String shapeType = tokens[0];
                    // Remove quotes from color value
                    String color = tokens[1].replaceAll("\"", "");
                    double x = Double.parseDouble(tokens[2]);
                    double y = Double.parseDouble(tokens[3]);
                    double z = Double.parseDouble(tokens[4]);
                    double size = Double.parseDouble(tokens[5]);

                    if (shapeType.equals("Circle")) {
                        gameEngine.addShape(new Circle(color, x, y, size));
                    } else if (shapeType.equals("Square")) {
                        gameEngine.addShape(new Square(color, x, y, size));
                    } else if (shapeType.equals("Sphere")) {
                        gameEngine.addShape(new Sphere(color, x, y, z, size));
                    } else if (shapeType.equals("Cube")) {
                        gameEngine.addShape(new Cube(color, x, y, z, size));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading data from file.");
            }
        }
    }

}

package com.ite409.assignmenttwo.gui.AssignmentTwo.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes.*;

public class DatabaseOperations {

    public void createShapesTable() {

        try (Connection conn = DriverManager.getConnection(Globals.dbUri, Globals.dbUsername, Globals.dbPassword)) {
            String sql = "CREATE TABLE IF NOT EXISTS shapes (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "shape_type VARCHAR(255)," + "color VARCHAR(255)," + "size DOUBLE," + "x DOUBLE," + "y DOUBLE,"
                    + "z DOUBLE," + "side DOUBLE" + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveToDatabase(List<Shape> shapes) {
        String dbUri = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
        String dbUsername = "root";
        String dbPassword = "1234";

        try (Connection conn = DriverManager.getConnection(dbUri, dbUsername, dbPassword)) {
            String sql = "INSERT INTO shapes (shape_type, id, color, size, x, y, z, side) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (Shape shape : shapes) {
                pstmt.setString(1, shape.getClass().getSimpleName());
                pstmt.setInt(2, shape.getId());
                pstmt.setString(3, shape.getColor());
                pstmt.setDouble(4, shape.calculateSize());
                pstmt.setDouble(5, shape.getX());
                pstmt.setDouble(6, shape.getY());
                pstmt.setDouble(7, shape.getZ());
                pstmt.setDouble(8, shape.getSide());

                pstmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Data saved to database successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving data to database.");
        }
    }

    public void loadFromDatabase(List<Shape> shapes, DefaultTableModel model) {
        String dbUri = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
        String dbUsername = "root";
        String dbPassword = "1234";
        try (Connection conn = DriverManager.getConnection(dbUri, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM shapes";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String shapeType = rs.getString("shape_type");
                int id = rs.getInt("id");
                String color = rs.getString("color");
                double size = rs.getDouble("size");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                boolean shapeExists = false;
                for (Shape shape : shapes) {
                    if (shape.getId() == id) {
                        shapeExists = true;
                        break;
                    }
                }

                if (!shapeExists) {
                    Shape shape = null;
                    if (shapeType.equalsIgnoreCase("Cube")) {
                        shape = new Cube(color, x, y, z, size);
                    } else if (shapeType.equalsIgnoreCase("Sphere")) {
                        shape = new Sphere(color, x, y, z, size);
                    } else if (shapeType.equalsIgnoreCase("Circle")) {
                        shape = new Circle(color, x, y, size);
                    } else if (shapeType.equalsIgnoreCase("Square")) {
                        shape = new Square(color, x, y, size);
                    }

                    if (shape != null) {
                        shapes.add(shape);
                        model.addRow(new Object[] { shape.getClass().getSimpleName(), id, color, size, x, y, z });
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded from database successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database.");
        }
    }
}

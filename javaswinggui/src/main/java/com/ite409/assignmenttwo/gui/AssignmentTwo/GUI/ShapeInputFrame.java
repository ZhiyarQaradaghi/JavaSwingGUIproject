package com.ite409.assignmenttwo.gui.AssignmentTwo.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes.*;

public class ShapeInputFrame extends JFrame {
    private final String title = "Shape - Detail Input";
    private final GameEngine engine;
    private String colorString = "";

    public ShapeInputFrame() {
        engine = new GameEngine();
        init();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(ShapeInputFrame.this,
                "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public void init() {
        this.setTitle(title);
        JButton colorButton = new JButton("Choose a Color");
        JTextArea jcolorarea = new JTextArea();
        JTextArea jtx3 = new JTextArea(); // x
        JTextArea jtx4 = new JTextArea(); // y
        JTextArea jtx5 = new JTextArea(); // z
        JTextArea jtx6 = new JTextArea(); // side or radius

        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        JLabel jl4 = new JLabel();
        JLabel jl5 = new JLabel(); // x
        JLabel jl6 = new JLabel(); // y
        JLabel jl7 = new JLabel(); // z
        JLabel jl8 = new JLabel();
        JLabel jl9 = new JLabel(); // side or radius

        this.add(colorButton);
        colorButton.setBounds(100, 70, 150, 30);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(ShapeInputFrame.this, "Choose Shape Color", Color.WHITE);
                if (color != null) {
                    jcolorarea.setBackground(color);
                    colorString = "RGB: " + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
                    jcolorarea.setText(colorString);
                }
            }
        });

        this.add(jcolorarea);
        this.add(jtx3);
        this.add(jtx4);
        this.add(jtx5);
        this.add(jtx6);

        jcolorarea.setBounds(260, 70, 170, 30);
        jtx3.setBounds(120, 200, 90, 20);
        jtx4.setBounds(230, 200, 90, 20);
        jtx5.setBounds(340, 200, 90, 20);
        jtx6.setBounds(100, 260, 330, 30);

        this.add(jl1);
        this.add(jl2);
        this.add(jl3);
        this.add(jl4);
        this.add(jl5);
        this.add(jl6);
        this.add(jl7);
        this.add(jl8);
        this.add(jl9);

        jl1.setBounds(100, 10, 400, 25);
        jl2.setBounds(100, 50, 400, 20);
        jl3.setBounds(100, 110, 400, 20);
        jl4.setBounds(180, 170, 400, 20);
        jl5.setBounds(100, 200, 400, 20);
        jl6.setBounds(215, 200, 400, 20);
        jl7.setBounds(325, 200, 400, 20);
        jl8.setBounds(350, 217, 400, 20);
        jl9.setBounds(100, 240, 400, 20);

        jl1.setText("Input the following information");
        jl1.setFont(new Font(null, Font.BOLD, 20));
        jl2.setText("Shape Color: ");
        jl3.setText("Enter Shape Type: ");
        jl4.setText("- Enter Shape Coordinates - ");
        jl5.setText("x : ");
        jl6.setText("y : ");
        jl7.setText("z : ");
        jl8.setText("(If shape is 3d)");
        jl8.setFont(new Font(null, Font.PLAIN, 11));
        jl9.setText("Side Length/Radius:");

        jcolorarea.setEditable(false);

        String[] shapeOptions = { "Cube", "Sphere", "Circle", "Square" };
        JComboBox<String> jcb1 = new JComboBox<>(shapeOptions);

        this.add(jcb1);
        jcb1.setBounds(100, 130, 330, 30);

        JButton jb = new JButton();
        this.add(jb);
        jb.setBounds(190, 330, 120, 40);
        jb.setText("Store Shape");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = 0.0;
                double y = 0.0;
                double z = 0.0;
                double side = 0.0;

                try {
                    if (!jtx3.getText().isEmpty()) {
                        x = Double.parseDouble(jtx3.getText());
                    }
                    if (!jtx4.getText().isEmpty()) {
                        y = Double.parseDouble(jtx4.getText());
                    }
                    if (!jtx5.getText().isEmpty()) {
                        z = Double.parseDouble(jtx5.getText());
                    }
                    if (!jtx6.getText().isEmpty()) {
                        side = Double.parseDouble(jtx6.getText());
                    }

                    String type = jcb1.getSelectedItem().toString();
                    Shape shape = null;
                    if (type.equalsIgnoreCase("Cube")) {
                        shape = new Cube(colorString, x, y, z, side);
                    } else if (type.equalsIgnoreCase("Sphere")) {
                        shape = new Sphere(colorString, x, y, z, side);
                    } else if (type.equalsIgnoreCase("Circle")) {
                        shape = new Circle(colorString, x, y, side);
                    } else if (type.equalsIgnoreCase("Square")) {
                        shape = new Square(colorString, x, y, side);
                    }

                    if (shape != null) {
                        engine.addShape(shape);

                        // just to clear everything after an add
                        jcolorarea.setBackground(Color.WHITE);
                        jcolorarea.setText("");
                        jtx3.setText("");
                        jtx4.setText("");
                        jtx5.setText("");
                        jtx6.setText("");
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid input for numeric values.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error storing shape.");
                }
            }
        });

        this.setBounds(1000, 100, 510, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}

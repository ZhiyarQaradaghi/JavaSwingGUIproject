package com.ite409.assignmenttwo.gui.AssignmentTwo.GUI;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class MainMenu extends JFrame {
// private static MainMenu instance;

// // Make the constructor public
// public MainMenu() {
// // Set up the main frame
// this.setTitle("Assignment 2 - Main Menu");
// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// this.setBounds(200, 200, 500, 200);
// this.setLayout(null); // Set layout to null for absolute positioning

// // Create the ShapeInputFrame button
// JButton shapeInputButton = new JButton("Open Shape Input");
// shapeInputButton.setBounds(100, 30, 200, 30); // Set bounds manually
// shapeInputButton.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// // Open ShapeInputFrame
// SwingUtilities.invokeLater(new Runnable() {
// public void run() {
// ShapeInputFrame.getInstance().init();
// }
// });
// }
// });

// // Create the GameEngine button
// JButton gameEngineButton = new JButton("Open Game Engine");
// gameEngineButton.setBounds(100, 80, 200, 30); // Set bounds manually
// gameEngineButton.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// // Open GameEngine
// SwingUtilities.invokeLater(new Runnable() {
// public void run() {
// GameEngine.getInstance().init();
// GameEngine.getInstance().setVisible(true);
// }
// });
// }
// });

// // Add buttons to the main frame
// this.add(shapeInputButton);
// this.add(gameEngineButton);
// }

// // Make the showMenu() method public and static
// public static void showMenu() {
// getInstance().setVisible(true);
// }

// // Make the getInstance() method public and static
// public static MainMenu getInstance() {
// if (instance == null) {
// instance = new MainMenu();
// }
// return instance;
// }
// }

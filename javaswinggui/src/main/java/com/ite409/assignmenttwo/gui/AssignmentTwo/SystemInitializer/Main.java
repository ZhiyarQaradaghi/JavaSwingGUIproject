package com.ite409.assignmenttwo.gui.AssignmentTwo.SystemInitializer;

import javax.swing.SwingUtilities;

import com.ite409.assignmenttwo.gui.AssignmentTwo.GUI.ShapeInputFrame;
// By Zhiyar Burhan Mahmud #21012 and Kevin Aso Faraj #21027

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ShapeInputFrame();
        });
    }
}

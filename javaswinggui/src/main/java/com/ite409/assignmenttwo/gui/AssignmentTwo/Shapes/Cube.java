package com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes;

import java.io.Serializable;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Exceptions.InvalidColorException;

public class Cube extends Shape implements Serializable {
    public double[] threeDCoordinates;
    private double sideLength;
    private static String color;

    public Cube(String color, double x, double y, double z, double sideLength) throws InvalidColorException {
        super(color, x, y, z, sideLength);
        this.color = color;
        this.threeDCoordinates = new double[] { x, y, z };
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double[] getThreeDCoordinates() {
        return threeDCoordinates;
    }

    public double calculateVolume() { // volume = sides^3
        return sideLength * sideLength * sideLength;
    }

    @Override
    public double calculateSize() { // area = 6*sides^2
        return 6 * sideLength * sideLength;
    }

    @Override
    protected String getShapeType() {
        return "Cube";
    }

}

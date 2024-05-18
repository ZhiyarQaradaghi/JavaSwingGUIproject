package com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes;

import java.io.Serializable;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Exceptions.InvalidColorException;

public class Sphere extends Shape implements Serializable {
    public double[] threeDCoordinates;
    private double radius;

    public Sphere(String color, double x, double y, double z, double radius) throws InvalidColorException {
        super(color, x, y, z, radius);
        this.threeDCoordinates = new double[] { x, y, z };
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double[] getThreeDCoordinates() {
        return threeDCoordinates;
    }

    public double calculateVolume() { // Volume 4/3*πr3
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double calculateSize() { // Surface Area=4πr^2
        return 4 * Math.PI * radius * radius;
    }

    @Override
    protected String getShapeType() {
        return "Sphere";
    }
}

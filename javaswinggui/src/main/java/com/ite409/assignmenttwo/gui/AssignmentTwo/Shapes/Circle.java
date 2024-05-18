package com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes;

import java.io.Serializable;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Exceptions.InvalidColorException;

public class Circle extends Shape implements Serializable {
    private double radius;
    private double[] TwoDCoordinates;

    public Circle(String color, double x, double y, double radius) throws InvalidColorException {
        super(color, x, y, 0, radius);
        this.TwoDCoordinates = new double[] { x, y };
        this.radius = radius;
    }

    public double[] getTwoDCoordinates() {
        return TwoDCoordinates;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateSize() { // Surface Area = π*r^2
        return Math.PI * radius * radius;
    }

    @Override
    protected String getShapeType() {
        return "Circle";
    }
}

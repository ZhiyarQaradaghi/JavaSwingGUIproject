package com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes;

import java.io.Serializable;

import com.ite409.assignmenttwo.gui.AssignmentTwo.Exceptions.*;

public class Square extends Shape implements Serializable {
    private double sideLength;
    private double[] TwoDCoordinates;

    public Square(String color, double x, double y, double sideLength)
            throws ValidationException, InvalidColorException {
        super(color, x, y, 0, sideLength);
        if (sideLength <= 0) {
            throw new ValidationException("Side length must be positive.");
        }
        this.TwoDCoordinates = new double[] { x, y };
        this.sideLength = sideLength;
    }

    public double[] getTwoDCoordinates() {
        return TwoDCoordinates;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double calculateSize() { // Area of square : s^2
        return sideLength * sideLength;
    }

    @Override
    protected String getShapeType() {
        return "Square";
    }
}

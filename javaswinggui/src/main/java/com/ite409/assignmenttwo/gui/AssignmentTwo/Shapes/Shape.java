package com.ite409.assignmenttwo.gui.AssignmentTwo.Shapes;

import java.util.Random;

public abstract class Shape {
    private final int id;
    private String color;
    private double x, y, z;
    private double side;

    public Shape(String color, double x, double y, double z, double side) {
        this.id = new Random().nextInt(Integer.MAX_VALUE);
        this.x = x;
        this.y = y;
        this.z = z;
        this.side = side;
        setColor(color);
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSide() {
        return side;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public abstract double calculateSize();

    protected abstract String getShapeType();

    @Override
    public String toString() {
        return String.format("%s,%s,%.2f,%.2f,%.2f,%.2f", getShapeType(), getColor(), getX(), getY(), getZ(),
                calculateSize());
    }

}

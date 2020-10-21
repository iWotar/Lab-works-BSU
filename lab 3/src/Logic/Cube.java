package Logic;

import java.util.Arrays;
import java.util.Vector;

public class Cube implements Shape {
    private final double size;

    public Cube(double size) {
        this.size = size;
    }

    public Cube(Vector<Double> sizes){this.size = sizes.get(0);}

    @Override
    public double volume() {
        return Math.pow(size, 3);
    }

    @Override
    public Vector<Vector<Integer>> printData() {
        Vector<Vector<Integer>> cord = new Vector<>();
        cord.add(new Vector<>(Arrays.asList(10, 10, 100, 10)));
        cord.add(new Vector<>(Arrays.asList(100, 10, 100, 100)));
        cord.add(new Vector<>(Arrays.asList(100, 100, 10, 100)));
        cord.add(new Vector<>(Arrays.asList(10, 100, 10, 10)));
        return cord;
    }

    @Override
    public String getType() {
        return "Cube";
    }

    @Override
    public String getSize() {
        return String.valueOf(size);
    }

    @Override
    public String sizeInfo() {
        return "сторона = " + size;
    }

}

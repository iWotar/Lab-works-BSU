package Logic;

import java.util.Arrays;
import java.util.Vector;

public class Pyramid implements Shape {
    private final double platf;
    private final double height;

    public Pyramid(double platf, double height) {
        this.platf = platf;
        this.height = height;
    }
    public Pyramid(Vector<Double> sizes) {
        platf = sizes.get(0);
        height = sizes.get(1);
    }

    @Override
    public double volume() {
        return 1. / 3 * height * platf * platf;
    }

    @Override
    public Vector<Vector<Integer>> printData() {
        Vector<Vector<Integer>> cord = new Vector<>();
        cord.add(new Vector<>(Arrays.asList(50, 10, 100, 100)));
        cord.add(new Vector<>(Arrays.asList(100, 100, 10, 100)));
        cord.add(new Vector<>(Arrays.asList(10, 100, 50, 10)));
        return cord;
    }

    @Override
    public String getType() {
        return "Pyramid";
    }

    @Override
    public String getSize() {
        return height + ", " + platf;
    }

    @Override
    public String sizeInfo() {
        return "высота = " + height + ", основание = " + platf;
    }
}

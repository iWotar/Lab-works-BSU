package Logic;

import java.util.Arrays;
import java.util.Vector;

public class Sphere implements Shape {
    private final double rad;

    public Sphere(double rad) {
        this.rad = rad;
    }

    @Override
    public double volume() {
        return 4. / 3 * 3.14 * Math.pow(rad, 3);
    }

    @Override
    public Vector<Vector<Integer>> printData() {
        Vector<Vector<Integer>> cord = new Vector<>();
        cord.add(new Vector<>(Arrays.asList(1, 1, 100, 100, 1)));
        return cord;
    }

    @Override
    public String getType() {
        return "Sphere";
    }

    @Override
    public String getSize() {
        return "радиус = " + rad;
    }
}

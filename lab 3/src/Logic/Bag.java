package Logic;

import java.util.Vector;

public class Bag {
    private final double capacity = 1000;
    private double curCapacity = capacity;
    private final Vector<Shape> content = new Vector<>();

    public double getCurCapacity() {
        return curCapacity;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            out.append("<html>").append(i + 1).append(": ").append("Класс: ").append(content.elementAt(i).getType());
            out.append("<br>Размеры: ").append(content.elementAt(i).getSize());
            out.append("<br>Объем: ").append(content.elementAt(i).volume()).append("<br><br>");
        }
        return out.toString();
    }

    public Bag() {
    }

    public void deleteShape(int ind) {
        curCapacity += content.elementAt(ind).volume();
        content.remove(ind);
    }

    public int getNumOfShapes() {
        return content.size();
    }

    public void checkCapacity() throws CapacityExeption {
        if (curCapacity < 0) {
            throw new CapacityExeption("Bag overflow");
        }
    }

    public void addShape(Shape obj) {
        curCapacity -= obj.volume();
        try {
            checkCapacity();
        } catch (CapacityExeption capacityExeption) {
            capacityExeption.printStackTrace();
            curCapacity += obj.volume();
            return;
        }
        content.add(obj);
        content.sort((s1, s2) -> Double.compare(s2.volume(), s1.volume()));
    }

    public void addShape(ShapeType type, double size) {
        switch (type) {
            case CUBE -> addShape(new Cube(size));
            case SPHERE -> addShape(new Sphere(size));
        }
    }

    public void addShape(ShapeType type, double platf, double heihgt) {
        if (type == ShapeType.PYRAMID) {
            addShape(new Pyramid(platf, heihgt));
        }
    }

    public static class CapacityExeption extends Exception {
        private final String message;

        public CapacityExeption(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}

package Logic;

import java.util.Vector;

public interface Shape {
    double volume();
    Vector<Vector<Integer>> printData(); // Это для рисования для будущих версий
    String getType();
    String getSize();
    String sizeInfo();
}

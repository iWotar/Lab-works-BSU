package Logic;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Vector;
import java.util.regex.Pattern;

public class Bag {
    private final double capacity = 100000;
    private double curCapacity = capacity;
    private final Vector<Shape> content = new Vector<>();

    public double getCurCapacity() {
        return curCapacity;
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("res\\data.xml");
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<ShapeCatalogue>\n" +
                    "</ShapeCatalogue>");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbuilder.parse("res\\data.xml");

            for (Shape shape : content) {
                saveShape(doc, shape);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void saveShape(Document doc, Shape shape) {
        Element root = doc.getDocumentElement();
        Element sh_node = doc.createElement("Shape");
        sh_node.setAttribute("type", shape.getType());
        sh_node.setAttribute("size", shape.getSize());

        root.appendChild(sh_node);

        writeDoc(doc);
    }

    private void writeDoc(Document doc) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(doc);
            FileOutputStream fos = new FileOutputStream("res\\data.xml");

            StreamResult sr = new StreamResult(fos);
            tr.transform(domSource, sr);
        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse("res\\data.xml");

            Node root = doc.getDocumentElement();
            NodeList shapes = root.getChildNodes();

            content.clear();
            curCapacity = capacity;

            int num = shapes.getLength();
            for (int i = 0; i < num; i++) {
                Node shape = shapes.item(i);
                if (shape.getNodeType() != Node.TEXT_NODE) {
                    parseShape(shape);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void parseShape(Node shape) {
        int n = shape.getAttributes().getLength();
        ShapeType type = null;
        Vector<Double> sizes = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (shape.getAttributes().item(i).getNodeName().equals("type")) {
                switch (shape.getAttributes().item(i).getTextContent()) {
                    case ("Cube") -> type = ShapeType.CUBE;
                    case ("Pyramid") -> type = ShapeType.PYRAMID;
                    case ("Sphere") -> type = ShapeType.SPHERE;
                }
            } else if (shape.getAttributes().item(i).getNodeName().equals("size")) {
                String data = shape.getAttributes().item(i).getTextContent();
                Pattern doub = Pattern.compile("\\s*,\\s*");
                String[] doubls = doub.split(data);
                for (String doubl : doubls) {
                    sizes.add(Double.parseDouble(doubl));
                }
            }
        }
        if (type != null) {
            addShape(type, sizes);
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            out.append("<html>").append(i + 1).append(": ").append("Класс: ").append(content.elementAt(i).getType());
            out.append("<br>Размеры: ").append(content.elementAt(i).sizeInfo());
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

    public void addShape(ShapeType type, Vector<Double> sizes) {
        switch (type) {
            case CUBE -> addShape(new Cube(sizes));
            case SPHERE -> addShape(new Sphere(sizes));
            case PYRAMID -> addShape(new Pyramid(sizes));
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

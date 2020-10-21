package View;

import Logic.Bag;
import Logic.ShapeType;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainView {
    private final JLabel label = new JLabel();
    private final JLabel cap_label = new JLabel();
    private Bag bag = null;

    public MainView() {
        JFrame frame = new JFrame("Simple Example");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.add(panel);
        formMainPanel(panel);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        formMenuBar(menuBar);

        frame.revalidate();
    }

    private void formMenuBar(JMenuBar menuBar) {
        JMenu file = new JMenu("file");
        file.setMnemonic('f');
        JMenu info = new JMenu("info");
        info.setMnemonic('i');

        JMenuItem save = new JMenuItem("save");
        save.setMnemonic('s');
        JMenuItem load = new JMenuItem("load");
        load.setMnemonic('l');
        JMenuItem exit = new JMenuItem("exit");
        exit.setMnemonic('e');

        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(exit);

        tuneFileMenu(file);

        JMenuItem help = new JMenuItem("help");
        help.setMnemonic('h');
        JMenuItem about = new JMenuItem("about");
        about.setMnemonic('a');

        info.add(help);
        info.add(about);

        tuneInfoMenu(info);

        menuBar.add(file);
        menuBar.add(info);
    }

    private void tuneInfoMenu(JMenu info) {
        int n = info.getItemCount();
        JMenuItem element;
        for (int i = 0; i < n; i++) {
            element = info.getItem(i);
            if (element == null) {
                continue;
            }
            switch (element.getText()) {
                case ("help") -> element.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpProcessing(info);
                    }
                });
                case ("about") -> element.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aboutProcessing(info);
                    }
                });
                default -> System.out.println("untreated JMenuItem");
            }
        }
    }

    private void aboutProcessing(JMenu info) {
        JOptionPane.showMessageDialog(info, "Приложение для работы с массивом фигур.\n\n v0.2");
    }

    private void helpProcessing(JMenu info) {
        JOptionPane.showMessageDialog(info, "Приложение позволяет добавлять, удалять, сохранять и загружать фигуры.");
    }

    private void tuneFileMenu(JMenu file) {
        int n = file.getItemCount();
        JMenuItem element;
        for (int i = 0; i < n; i++) {
            element = file.getItem(i);
            if (element == null) {
                continue;
            }
            switch (element.getText()) {
                case ("save") -> element.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveProcessing(file);
                    }
                });
                case ("load") -> element.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadProcessing(file);
                    }
                });
                case ("exit") -> element.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitProcessing();
                    }
                });
                default -> System.out.println("untreated JMenuItem");
            }
        }
    }

    private void exitProcessing() {
        System.out.println("exit");
        System.exit(0);
    }

    private void loadProcessing(JMenu file) {
        System.out.println("load");

        String path = getPath(file);
        if (!path.isEmpty()){
            bag.load(path);
            newData();
        }
    }

    private String getPath(JMenu file) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Source (.xml)", "xml");
        JFileChooser fileChooser = new JFileChooser(".\\res");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(file);
        String path = "";
        if (result == JFileChooser.APPROVE_OPTION ) {
            path = fileChooser.getSelectedFile().getPath();
            if (!path.matches(".*\\.(xml)")) {
                Pattern p = Pattern.compile(".+[^.]");
                Matcher m = p.matcher(path);
                if (m.find()) {
                    path = path.substring(m.start(), m.end());
                    path = path.concat(".xml");
                }
            }
        }
        return path;
    }

    private void saveProcessing(JMenu file) {
        String path = getPath(file);
        if (!path.isEmpty()) {
            bag.save(path);
        }
    }

    private void formMainPanel(JPanel panel) {
        JButton add_shape = new JButton("Add shape");
        add_shape.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcessing(panel);
            }
        });

        JButton delete_shape = new JButton("Delete shape");
        delete_shape.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delShapeIndexes(panel);
            }
        });

        panel.setLayout(new BorderLayout());
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1, 2));

        button_panel.add(add_shape);
        button_panel.add(delete_shape);
        panel.add(button_panel, BorderLayout.SOUTH);
        panel.add(cap_label, BorderLayout.NORTH);

        JPanel forText = new JPanel();
        forText.setLayout(new BorderLayout());
        forText.add(label);
        JScrollPane scrollPane = new JScrollPane(label);

        forText.add(scrollPane, BorderLayout.CENTER);

        panel.add(forText);
    }

    private void addProcessing(JPanel panel) {
        int optionDialog = JOptionPane.showOptionDialog(panel, "Выберите вид: ",
                "Adding shape", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"Cube", "Pyramid", "Sphere"}, "Cube");
        switch (optionDialog) {
            case (0) -> takeShapeInput(panel, ShapeType.CUBE);
            case (1) -> takeShapeInput(panel, ShapeType.PYRAMID);
            case (2) -> takeShapeInput(panel, ShapeType.SPHERE);
        }
    }

    private void delShapeIndexes(JPanel panel) {
        String s_indexes = (JOptionPane.showInputDialog(panel, "<html>Введите индексы: <br><font size=\"2\">" +
                "(Через запятую возможен множественный ввод<br>Учитывайте сдвиг при удалении предыдущих элементов)</font><br>"));
        var indexes = checkIndexes(s_indexes, panel);
        if (indexes == null) {
            return;
        }
        for (Integer ind : indexes) {
            try {
                bag.deleteShape(ind);
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(panel, "Неправильный индекс. После удаление предыдущих" +
                        " элементов индекса уже не стало.", "Error", JOptionPane.WARNING_MESSAGE);
                newData();
                return;
            }
        }
        newData();
    }

    private Vector<Integer> checkIndexes(String s_indexes, JPanel panel) {
        Vector<Integer> data = new Vector<>();
        if (s_indexes == null) return null;
        if (s_indexes.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Пустой ввод", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        var param = s_indexes.split("\\s*[,]\\s*");
        try {
            for (String s : param) {
                data.add(Integer.parseInt(s) - 1);
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(panel, "Неправильный ввод", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        for (Integer datum : data) {
            if (datum < 0 || datum >= bag.getNumOfShapes()) {
                JOptionPane.showMessageDialog(panel, "Неправильный индекс", "Error", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        return data;
    }

    private boolean isDouble(String[] mas) {
        for (String el : mas) {
            if (!el.matches("\\d+[.]?\\d*")) return false;
        }
        return true;
    }

    private void takeShapeInput(JPanel panel, ShapeType type) {
        String inpParam = "";
        switch (type) {
            case PYRAMID -> inpParam = "высоту и длину основания";
            case SPHERE -> inpParam = "радиус";
            case CUBE -> inpParam = "длину стороны";
        }
        String s_size = (JOptionPane.showInputDialog(panel, "<html>Введите " + inpParam + ": <br><font size=\"2\">" +
                "(Через запятую возможен множественный ввод)</font><br>"));
        var sizes = checkShapeInput(panel, s_size);

        if (sizes == null) return;

        if (Arrays.asList(new ShapeType[]{ShapeType.CUBE, ShapeType.SPHERE}).contains(type)) {
            for (Double s : sizes) {
                bag.addShape(type, s);
            }
        } else {
            for (int i = 0; i < sizes.size() - 1; i += 2) {
                bag.addShape(ShapeType.PYRAMID, sizes.elementAt(i), sizes.elementAt(i + 1));
            }
        }
        newData();
    }

    private Vector<Double> checkShapeInput(JPanel panel, String s_size) {
        if (s_size == null) return null;
        if (s_size.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Пустой ввод", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        var param = s_size.split("\\s*[,]\\s*");
        if (!isDouble(param)) {
            JOptionPane.showMessageDialog(panel, "Неправильный ввод", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Vector<Double> data = new Vector<>();
        for (String s : param) {
            data.add(Double.parseDouble(s));
        }
        return data;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
        label.setText(bag.toString());
    }

    public void newData() {
        label.setText(bag.toString());
        cap_label.setText(" Осталось места: " + bag.getCurCapacity());
    }
}




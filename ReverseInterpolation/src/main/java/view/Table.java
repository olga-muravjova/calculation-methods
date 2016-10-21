package view;

/**
 * Created by Ольга on 21.09.2016.
 */

import model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Table extends JFrame {

    private static final String[] COL_NAMES = {
            "x",
            "f(x)",
    };

    public static void createGUI(ArrayList<Node> nodes, String tableName) {
        JFrame frame = new JFrame(tableName);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String[][] data = convertListToStringArray(nodes);

        JTable table = new JTable(data, COL_NAMES);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    private static String[][] convertListToStringArray(ArrayList<Node> nodes) {
        String[][] data = new String[nodes.size()][2];
        int i = 0;
        for (Node node : nodes) {
            data[i][0] = String.valueOf(node.getX());
            data[i][1] = String.valueOf(node.getY());
            i++;
        }
        return data;
    }
}

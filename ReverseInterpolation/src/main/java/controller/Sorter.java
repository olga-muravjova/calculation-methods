package controller;

import model.Node;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Ольга on 08.10.2016.
 */
public class Sorter {

    public static ArrayList<Node> sortListOfNodes(ArrayList<Node> nodes, Double x) {
        nodes.sort((Node n1, Node n2) -> Double.compare(abs(n1.getX() - x), abs(n2.getX() - x)));
        return nodes;
    }

    public static ArrayList<Node> sortListOfNodesByF(ArrayList<Node> nodes, Double f) {
        nodes.sort((Node n1, Node n2) -> Double.compare(abs(n1.getY() - f), abs(n2.getY() - f)));
        return nodes;
    }
}

package controller.interpolators;

import model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ольга on 21.09.2016.
 */
public class LagrangeInterpolator {

    private ArrayList<Node> nodes;
    private int polynomeDegree;

    public LagrangeInterpolator(ArrayList<Node> nodes, int polynomeDegree) {
        this.nodes = nodes;
        this.polynomeDegree = polynomeDegree;
    }

    public Double interpolate(Double x) {
        List<Node> subList = nodes.subList(0, polynomeDegree);
        Double result = interpolate(x, subList);
        return result;
    }

    private Double interpolate(Double x, List<Node> selectedNodes) {
        Double result = 0.0;
        for (int i = 0; i < selectedNodes.size(); i++) {
            result += selectedNodes.get(i).getY() * omega(i, x, selectedNodes) /
                    omega(i, selectedNodes.get(i).getX(), selectedNodes);
        }
        return result;
    }

    private Double omega(int k, Double x, List<Node> selectedNodes) {
        double result = 1;
        for (int i = 0; i < selectedNodes.size(); i++) {
            if (i != k) {
                result *= x - selectedNodes.get(i).getX();
            }
        }
        return result;
    }
}

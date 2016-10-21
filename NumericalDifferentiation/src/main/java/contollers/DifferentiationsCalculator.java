package contollers;

import model.Node;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by Ольга on 22.10.2016.
 */
public class DifferentiationsCalculator {

    private Double step;

    public DifferentiationsCalculator(Double step) {
        this.step = step;
    }

    public void calculateDifferentiations(ArrayList<Node> nodes) {
        if (nodes.size() <= 2) {
            throw new RuntimeException();
        }
        //for the first node
        Node first = nodes.get(0);
        first.setFirstDerivation
                (calculateFirstDiffOfFirstX(first.getY(), nodes.get(1).getY(), nodes.get(2).getY()));
        first.setSecondDerivation
                (calculateSecondDiffOfFirstX(first.getY(), nodes.get(1).getY(), nodes.get(2).getY()));

        //for middle nodes
        for (int i = 1; i < nodes.size() - 1; i++) {
            nodes.get(i).setFirstDerivation
                    (calculateFirstDiffOfMiddleX(nodes.get(i + 1).getY(), nodes.get(i - 1).getY()));
            nodes.get(i).setSecondDerivation
                    (calculateSecondDiffOfMiddleX(nodes.get(i + 1).getY(), nodes.get(i).getY(), nodes.get(i - 1).getY()));
        }

        // for the last node
        Node last = nodes.get(nodes.size() - 1);
        last.setFirstDerivation
                (calculateFirstDiffOfLastX(last.getY(), nodes.get(nodes.size() - 2).getY(), nodes.get(nodes.size() - 3).getY()));
        last.setSecondDerivation
                (calculateSecondDiffOfLastX(last.getY(), nodes.get(nodes.size() - 2).getY(), nodes.get(nodes.size() - 3).getY()));
    }

    private Double calculateFirstDiffOfFirstX(Double first, Double second, Double third) {
        return (-3 * first + 4 * second - third) / (2 * step);
    }

    private Double calculateFirstDiffOfMiddleX(Double first, Double second) {
        return (first - second) / (2 * step);
    }

    private Double calculateFirstDiffOfLastX(Double first, Double second, Double third) {
        return (3 * first - 4 * second + third) / (2 * step);
    }

    private Double calculateSecondDiffOfFirstX(Double first, Double second, Double third) {
        return 0.0;
    }

    private Double calculateSecondDiffOfMiddleX(Double first, Double second, Double third) {
        return (first - 2 * second + third) / (step * step);
    }

    private Double calculateSecondDiffOfLastX(Double first, Double second, Double third) {
        return 0.0;
    }
}

package controllers;

import model.Node;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.nextDown;

/**
 * Created by Ольга on 09.10.2016.
 */
public class Interpolator {

    private ArrayList<Node> nodes;
    private int polynomeDegree;
    private List<List<Double>> finiteDifferenceTable;

    public Interpolator(ArrayList<Node> nodes, int polynomeDegree) {
        this.nodes = nodes;
        this.polynomeDegree = polynomeDegree;
    }

    public void createFiniteDifferenceTable() {
        finiteDifferenceTable = new ArrayList<>();
        //adding first colomn in divDiff table
        finiteDifferenceTable.add(new ArrayList<>());
        //filling first colomn in divDiff table
        for (int i = 0; i < nodes.size() - 1; i++) {
            finiteDifferenceTable.get(0).add(nodes.get(i + 1).getY() - nodes.get(i).getY());
        }
        for (int i = 0; i < finiteDifferenceTable.get(0).size() - 1; i++) {
            //adding new colomn in the table
            finiteDifferenceTable.add(new ArrayList<>());
            //filling new colomn
            for (int j = 0; j < finiteDifferenceTable.get(i).size() - 1; j++) {
                finiteDifferenceTable.get(i + 1).add
                        (finiteDifferenceTable.get(i).get(j + 1) - finiteDifferenceTable.get(i).get(j));
            }
        }
    }

    public Double interpolateInBeginning(Double x, Double step) {
        Double temp = (x - nodes.get(0).getX()) / step;
        Double result = nodes.get(0).getY();
        for (int colomn = 0; colomn < polynomeDegree; colomn++) {
            result += finiteDifferenceTable.get(colomn).get(0) * omegaBegin(temp, colomn) / factorial(colomn + 1);
        }
        return result;
    }

    //calculates t(t-1)(t-2)...
    private Double omegaBegin(Double temp, int k) {
        Double result = 1.0;
        for (int i = 0; i <= k; i++) {
            result *= temp - i;
        }
        return result;
    }

    private int factorial(int x) {
        int result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    public Double interpolateInEnd(Double x, Double step) {
        Double temp = (x - nodes.get(nodes.size() - 1).getX()) / step;
        Double result = nodes.get(nodes.size() - 1).getY();
        int line = finiteDifferenceTable.get(0).size() - 1;
        for (int colomn = 0; colomn < polynomeDegree; colomn++) {
            result += finiteDifferenceTable.get(colomn).get(line) * omegaEnd(temp, colomn) / factorial(colomn + 1);
            line--;
        }
        return result;
    }

    //calculates t(t+1)(t+2)...
    private Double omegaEnd(Double temp, int k) {
        Double result = 1.0;
        for (int i = 0; i <= k; i++) {
            result *= temp + i;
        }
        return result;
    }

    public Double interpolateInMiddle(Double x, Double step) {
        int nearestNodeNumber = getNearestNode(x);
        Double temp = (x - nodes.get(nearestNodeNumber).getX()) / step;
        Double result = nodes.get(nearestNodeNumber).getY();
        for (int colomn = 0; colomn < polynomeDegree; colomn++) {
            int line = nearestNodeNumber - (int) (nextDown((colomn + 3) / 2));
            result += finiteDifferenceTable.get(colomn).get(line) * omegaMiddle(temp, colomn) / factorial(colomn + 1);
        }
        return result;
    }


    // calculates t(t-1)(t+1)...
    private Double omegaMiddle(Double temp, int k) {
        Double result = temp;
        int param = -1;
        for (int i = 0; i < k; i++) {
            result *= temp + param;
            if (param < 0) {
                param = -param;
            } else {
                param = -(param + 1);
            }
        }
        return result;
    }

    //finds z0
    private int getNearestNode(Double x) {
        for (int i = 0; i < nodes.size(); i++) {
            if (x < nodes.get(i).getX()) {
                return i - 1;
            }
        }
        throw new RuntimeException();
    }
}

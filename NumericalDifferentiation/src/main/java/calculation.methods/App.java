package calculation.methods;

import constants.Constants;
import contollers.DifferentiationsCalculator;
import contollers.NodeCreator;
import model.Node;

import java.util.ArrayList;

import static view.Table.createGUI;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //create nodes
        NodeCreator nodesCreator = new NodeCreator(Constants.SEGMENT_START, Constants.SEGMENT_END,
                Constants.FUNC, Constants.FUNC_DIFF_FIRST, Constants.FUNC_DIFF_SECOND, Constants.NUMBER_OF_NODES);
        final ArrayList<Node> nodes = nodesCreator.createNodes();

        //calculate differentiations
        DifferentiationsCalculator differentiationsCalculator =
                new DifferentiationsCalculator(nodesCreator.getStep());
        differentiationsCalculator.calculateDifferentiations(nodes);


        final String sortedTableName = "First and second differentiations";
        //create table
        javax.swing.SwingUtilities.invokeLater(() -> createGUI(nodes, sortedTableName));
    }
}

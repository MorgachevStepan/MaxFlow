import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 11.10.2023 17:17
 */
public class GraphCreator {
    private final String SOURCE = "SOURCE";
    private final String DISTANT = "DISTANT";
    private final String X1 = "X1";
    private final String X2 = "X2";
    private final String X3 = "X3";
    private final String X4 = "X4";
    private final String X5 = "X5";
    private final String X6 = "X6";
    private final String X7 = "X7";
    private final String X8 = "X8";
    private final String X9 = "X9";
    private final String X10 = "X10";

    public GraphCreator() {
    }

    public void CreateGraph(Graph<String, DefaultWeightedEdge> graph) {
        CreateVertex(graph);
        CreateEdge(graph);
    }

    private void CreateEdge(Graph<String, DefaultWeightedEdge> graph) {
        DefaultWeightedEdge SX1 = graph.addEdge(SOURCE, X1);
        DefaultWeightedEdge SX2 = graph.addEdge(SOURCE, X2);
        DefaultWeightedEdge SX3 = graph.addEdge(SOURCE, X3);
        DefaultWeightedEdge SX4 = graph.addEdge(SOURCE, X4);
        DefaultWeightedEdge SX5 = graph.addEdge(SOURCE, X5);

        DefaultWeightedEdge X1X6 = graph.addEdge(X1, X6);
        DefaultWeightedEdge X1X7 = graph.addEdge(X1, X7);
        DefaultWeightedEdge X1X8 = graph.addEdge(X1, X8);
        DefaultWeightedEdge X1X9 = graph.addEdge(X1, X9);
        DefaultWeightedEdge X1X10 = graph.addEdge(X1, X10);

        DefaultWeightedEdge X2X6 = graph.addEdge(X2, X6);
        DefaultWeightedEdge X2X7 = graph.addEdge(X2, X7);
        DefaultWeightedEdge X2X8 = graph.addEdge(X2, X8);
        DefaultWeightedEdge X2X9 = graph.addEdge(X2, X9);
        DefaultWeightedEdge X2X10 = graph.addEdge(X2, X10);

        DefaultWeightedEdge X3X6 = graph.addEdge(X3, X6);
        DefaultWeightedEdge X3X7 = graph.addEdge(X3, X7);
        DefaultWeightedEdge X3X8 = graph.addEdge(X3, X8);
        DefaultWeightedEdge X3X9 = graph.addEdge(X3, X9);
        DefaultWeightedEdge X3X10 = graph.addEdge(X3, X10);

        DefaultWeightedEdge X4X6 = graph.addEdge(X4, X6);
        DefaultWeightedEdge X4X7 = graph.addEdge(X4, X7);
        DefaultWeightedEdge X4X8 = graph.addEdge(X4, X8);
        DefaultWeightedEdge X4X9 = graph.addEdge(X4, X9);
        DefaultWeightedEdge X4X10 = graph.addEdge(X4, X10);

        DefaultWeightedEdge X5X6 = graph.addEdge(X5, X6);
        DefaultWeightedEdge X5X7 = graph.addEdge(X5, X7);
        DefaultWeightedEdge X5X8 = graph.addEdge(X5, X8);
        DefaultWeightedEdge X5X9 = graph.addEdge(X5, X9);
        DefaultWeightedEdge X5X10 = graph.addEdge(X5, X10);

        DefaultWeightedEdge X6D = graph.addEdge(X6, DISTANT);
        DefaultWeightedEdge X7D = graph.addEdge(X7, DISTANT);
        DefaultWeightedEdge X8D = graph.addEdge(X8, DISTANT);
        DefaultWeightedEdge X9D = graph.addEdge(X9, DISTANT);
        DefaultWeightedEdge X10D = graph.addEdge(X10, DISTANT);

        List<DefaultWeightedEdge> edgeList = new ArrayList<>(Arrays.asList(SX1, SX2, SX3, SX4, SX5
                , X1X6, X1X7, X1X8, X1X9, X1X10
                , X2X6, X2X7, X2X8, X2X9, X2X10
                , X3X6, X3X7, X3X8, X3X9, X3X10
                , X4X6, X4X7, X4X8, X4X9, X4X10
                , X5X6, X5X7, X5X8, X5X9, X5X10
                , X6D, X7D, X8D, X9D, X10D));

        GeneratedWeight(edgeList, graph);
    }

    private void GeneratedWeight(List<DefaultWeightedEdge> edgeList, Graph<String, DefaultWeightedEdge> graph) {
        for(DefaultWeightedEdge edge: edgeList){
            graph.setEdgeWeight(edge, (int)(Math.random() * 10 + 1));
        }
    }

    private void CreateVertex(Graph<String, DefaultWeightedEdge> graph) {
        graph.addVertex(SOURCE);
        graph.addVertex(DISTANT);
        graph.addVertex(X1);
        graph.addVertex(X2);
        graph.addVertex(X3);
        graph.addVertex(X4);
        graph.addVertex(X5);
        graph.addVertex(X6);
        graph.addVertex(X7);
        graph.addVertex(X8);
        graph.addVertex(X9);
        graph.addVertex(X10);
    }

}

import org.jgrapht.Graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 21.10.2023 18:22
 */
public class ConstraintsMaker {
    private final BufferedWriter writer;
    private final Graph<String, MyWeightedEdge> graph;
    private final List<String> vertexWithSource;
    private final List<String> vertexWithDistant;


    public ConstraintsMaker(BufferedWriter writer, Graph<String, MyWeightedEdge> graph) {
        this.writer = writer;
        this.graph = graph;
        vertexWithSource = new ArrayList<>();
        vertexWithDistant = new ArrayList<>();
        makeAdjacencyArrays();
    }

    private void makeAdjacencyArrays() {
        for(MyWeightedEdge myWeightedEdge: graph.edgesOf(GraphCreator.SOURCE)){ //ищем вершины смежные с SOURCE
            vertexWithSource.add(graph.getEdgeTarget(myWeightedEdge));
        }
        for (MyWeightedEdge myWeightedEdge: graph.edgesOf(GraphCreator.DISTANT)){ //ищем вершины смежные с DISTANT
            vertexWithDistant.add(graph.getEdgeSource(myWeightedEdge));
        }
    }

    public void makeObjectiveFunction() throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("max: ");
        for (MyWeightedEdge edge : graph.edgesOf(GraphCreator.SOURCE)) {
            result.append("+X_").append(GraphCreator.SOURCE).append("_").append(graph.getEdgeTarget(edge));
        }
        result.append(";\n\n");
        String buffer = result.toString();
        writer.write(buffer.replace(" +", " "));
    }

    public void makeSimpleConstraints() throws IOException {
        for(String vertex: graph.vertexSet()){
            for(MyWeightedEdge edge: graph.edgesOf(vertex)){
                if(!vertex.equals(graph.getEdgeTarget(edge))) {
                    writer.write("X_" + vertex + "_" + graph.getEdgeTarget(edge) + "<=" + edge.toString() + ";\n");
                }
            }
        }
    }

    public void makeCompositeConstraints() throws IOException {
        StringBuilder result = null;
        for(String vertex: vertexWithSource){
            result = new StringBuilder("X_" + GraphCreator.SOURCE + "_" + vertex + "=");
            for(MyWeightedEdge myWeightedEdge: graph.edgesOf(vertex)){
                if(!graph.getEdgeTarget(myWeightedEdge).equals(vertex)) {
                    result.append("+X_").append(vertex).append("_").append(graph.getEdgeTarget(myWeightedEdge)); //создаю
                }                                                                                                //первый ряд ограничений
            }
            result.append(";\n");
            String buffer = result.toString();
            writer.write(buffer.replace("=+", "="));
        }
        writer.write("\n");

        for(String vertex: vertexWithDistant){
            result = new StringBuilder("X_" + vertex + "_" + GraphCreator.DISTANT + "=");
            for (MyWeightedEdge myWeightedEdge: graph.edgesOf(vertex)){
                if(!graph.getEdgeSource(myWeightedEdge).equals(vertex)){
                    result.append("+X_").append(graph.getEdgeSource(myWeightedEdge)).append("_").append(vertex); //создаю
                }                                                                                                //второй ряж ограничений
            }
            result.append(";\n");
            String buffer = result.toString();
            writer.write(buffer.replace("=+", "="));
        }
        writer.write("\n");
    }
}

import org.jgrapht.Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Stepan Morgachev
 * @date 02.10.2023 11:47
 */
public class Main {

    public static void main(String[] args){
        Graph<String, MyWeightedEdge> graph;
        Render render = new Render();
        Serialization serialization = new Serialization();
        GraphCreator graphCreator = new GraphCreator(serialization, render);
        graph = graphCreator.CreateGraph();


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("graph.lp"))) {
            writer.write("max: ");
            for (MyWeightedEdge edge : graph.edgesOf(GraphCreator.SOURCE)) {
                writer.write("+" + edge.toString() + "X_" + GraphCreator.SOURCE + "_" + graph.getEdgeTarget(edge));
            }
            writer.write(";\n\n");

            for(String vertex: graph.vertexSet()){
                for(MyWeightedEdge edge: graph.edgesOf(vertex)){
                    if(vertex.equals(graph.getEdgeSource(edge))) {
                        writer.write("X_" + vertex + "_" + graph.getEdgeTarget(edge) + "<=" + edge.toString() + ";\n");
                    }
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}

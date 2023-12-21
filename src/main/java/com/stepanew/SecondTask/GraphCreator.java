package com.stepanew.SecondTask;

import com.stepanew.FirstTask.MyWeightedEdge;
import com.stepanew.FirstTask.Render;
import com.stepanew.FirstTask.Serialization;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 21.12.2023 13:48
 */
public class GraphCreator {
    public static final String[] SOURCE = {"SOURCE1", "SOURCE2"};
    public static final String[] VERTEX = {"X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8"};
    public static final String[] DISTANT = {"DISTANT1", "DISTANT2", "DISTANT3", "DISTANT4"
            , "DISTANT5", "DISTANT6", "DISTANT7", "DISTANT8"};

    private final Serialization serialization;
    private final Render render;
    private final String GRAPH_TXT;

    public GraphCreator(Serialization serialization, Render render, String graphTXT) {
        this.serialization = serialization;
        this.render = render;
        this.GRAPH_TXT = graphTXT;
    }

    public Graph<String, MyWeightedEdge> CreateGraph() {
        Graph<String, MyWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(MyWeightedEdge.class);
        if(isExist()){
            graph = serialization.Deserialize();
        } else {
            CreateVertex(graph);
            CreateEdge(graph);
            serialization.Serialize(graph);
            render.RenderGraph(graph, "");
        }
        return graph;
    }

    public void completeGraph(Graph<String, MyWeightedEdge> graph){
        String source = "SOURCE";
        String distant = "DISTANT";

        graph.addVertex(source);
        graph.addVertex(distant);

        for(String s: SOURCE){
            MyWeightedEdge e = graph.addEdge(source, s);
            double weight = 0;

            for(MyWeightedEdge myWeightedEdge: graph.edgesOf(s)){
                weight += Double.parseDouble(myWeightedEdge.toString());
            }

            graph.setEdgeWeight(e, weight);
        }

        for(String s: DISTANT){
            MyWeightedEdge e = graph.addEdge(s, distant);
            double weight = 0;

            for(MyWeightedEdge myWeightedEdge: graph.edgesOf(s)){
                weight += Double.parseDouble(myWeightedEdge.toString());
            }

            graph.setEdgeWeight(e, weight);
        }
    }

    private boolean isExist() {
        File file = new File(GRAPH_TXT);
        return file.exists();
    }

    private void CreateEdge(Graph<String, MyWeightedEdge> graph) {
        List<MyWeightedEdge> edgeList = new ArrayList<>();

        for(String source: SOURCE){
            for (int i = 0; i < VERTEX.length / 2; i++){
                edgeList.add(graph.addEdge(source, VERTEX[i]));
            }
        }

        for(int i = 0; i < VERTEX.length / 2; i++){
            for (int j = VERTEX.length / 2; j < VERTEX.length; j++){
                edgeList.add(graph.addEdge(VERTEX[i], VERTEX[j]));
            }
        }

        for(int i = VERTEX.length / 2, k = 0; i < VERTEX.length; i++, k++){
            for (int j = 0; j < 2; j++){
                edgeList.add(graph.addEdge(VERTEX[i], DISTANT[k * 2 + j]));
            }
        }

        GeneratedWeight(edgeList, graph);
    }

    private void GeneratedWeight(List<MyWeightedEdge> edgeList, Graph<String, MyWeightedEdge> graph) {
        for(MyWeightedEdge edge: edgeList){
            graph.setEdgeWeight(edge, (int)(Math.random() * 10 + 1));
        }
    }

    private void CreateVertex(Graph<String, MyWeightedEdge> graph) {
        for(String s: SOURCE){
            graph.addVertex(s);
        }

        for(String s: DISTANT){
            graph.addVertex(s);
        }

        for(String s: VERTEX){
            graph.addVertex(s);
        }
    }
}

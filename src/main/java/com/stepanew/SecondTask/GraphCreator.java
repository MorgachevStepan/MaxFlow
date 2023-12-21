package com.stepanew.SecondTask;

import com.stepanew.FirstTask.MyWeightedEdge;
import com.stepanew.FirstTask.Render;
import com.stepanew.FirstTask.Serialization;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 21.12.2023 13:48
 */
public class GraphCreator {
    public static final String SOURCE1 = "SOURCE1";
    public static final String SOURCE2 = "SOURCE2";
    public static final String DISTANT1 = "DISTANT1";
    public static final String DISTANT2 = "DISTANT2";
    public static final String DISTANT3 = "DISTANT3";
    public static final String DISTANT4 = "DISTANT4";
    public static final String DISTANT5 = "DISTANT5";
    public static final String DISTANT6 = "DISTANT6";
    public static final String DISTANT7 = "DISTANT7";
    public static final String DISTANT8 = "DISTANT8";
    private final String X1 = "X1";
    private final String X2 = "X2";
    private final String X3 = "X3";
    private final String X4 = "X4";
    private final String X5 = "X5";
    private final String X6 = "X6";
    private final String X7 = "X7";
    private final String X8 = "X8";

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
            render.RenderGraph(graph);
        }
        return graph;
    }

    private boolean isExist() {
        File file = new File(GRAPH_TXT);
        return file.exists();
    }

    private void CreateEdge(Graph<String, MyWeightedEdge> graph) {
        MyWeightedEdge S1X1 = graph.addEdge(SOURCE1, X1);
        MyWeightedEdge S1X2 = graph.addEdge(SOURCE1, X2);
        MyWeightedEdge S1X3 = graph.addEdge(SOURCE1, X3);
        MyWeightedEdge S1X4 = graph.addEdge(SOURCE1, X4);

        MyWeightedEdge S2X1 = graph.addEdge(SOURCE2, X1);
        MyWeightedEdge S2X2 = graph.addEdge(SOURCE2, X2);
        MyWeightedEdge S2X3 = graph.addEdge(SOURCE2, X3);
        MyWeightedEdge S2X4 = graph.addEdge(SOURCE2, X4);

        MyWeightedEdge X1X5 = graph.addEdge(X1, X5);
        MyWeightedEdge X1X6 = graph.addEdge(X1, X6);
        MyWeightedEdge X1X7 = graph.addEdge(X1, X7);
        MyWeightedEdge X1X8 = graph.addEdge(X1, X8);

        MyWeightedEdge X2X5 = graph.addEdge(X2, X5);
        MyWeightedEdge X2X6 = graph.addEdge(X2, X6);
        MyWeightedEdge X2X7 = graph.addEdge(X2, X7);
        MyWeightedEdge X2X8 = graph.addEdge(X2, X8);

        MyWeightedEdge X3X5 = graph.addEdge(X3, X5);
        MyWeightedEdge X3X6 = graph.addEdge(X3, X6);
        MyWeightedEdge X3X7 = graph.addEdge(X3, X7);
        MyWeightedEdge X3X8 = graph.addEdge(X3, X8);

        MyWeightedEdge X4X5 = graph.addEdge(X4, X5);
        MyWeightedEdge X4X6 = graph.addEdge(X4, X6);
        MyWeightedEdge X4X7 = graph.addEdge(X4, X7);
        MyWeightedEdge X4X8 = graph.addEdge(X4, X8);

        MyWeightedEdge X5D1 = graph.addEdge(X5, DISTANT1);
        MyWeightedEdge X5D2 = graph.addEdge(X5, DISTANT2);
        MyWeightedEdge X6D3 = graph.addEdge(X6, DISTANT3);
        MyWeightedEdge X6D4 = graph.addEdge(X6, DISTANT4);
        MyWeightedEdge X7D5 = graph.addEdge(X7, DISTANT5);
        MyWeightedEdge X7D6 = graph.addEdge(X7, DISTANT6);
        MyWeightedEdge X8D7 = graph.addEdge(X8, DISTANT7);
        MyWeightedEdge X8D8 = graph.addEdge(X8, DISTANT8);

        List<MyWeightedEdge> edgeList = new ArrayList<>(Arrays.asList(S1X1, S1X2, S1X3, S1X4
                , S2X1, S2X2, S2X3, S2X4
                , X1X5, X1X6, X1X7, X1X8
                , X2X5, X2X6, X2X7, X2X8
                , X3X5, X3X6, X3X7, X3X8
                , X4X5, X4X6, X4X7, X4X8
                , X5D1, X5D2, X6D3, X6D4
                , X7D5, X7D6, X8D7, X8D8));

        GeneratedWeight(edgeList, graph);
    }

    private void GeneratedWeight(List<MyWeightedEdge> edgeList, Graph<String, MyWeightedEdge> graph) {
        for(MyWeightedEdge edge: edgeList){
            graph.setEdgeWeight(edge, (int)(Math.random() * 10 + 1));
        }
    }

    private void CreateVertex(Graph<String, MyWeightedEdge> graph) {
        graph.addVertex(SOURCE1);
        graph.addVertex(SOURCE2);
        graph.addVertex(DISTANT1);
        graph.addVertex(DISTANT2);
        graph.addVertex(DISTANT3);
        graph.addVertex(DISTANT4);
        graph.addVertex(DISTANT5);
        graph.addVertex(DISTANT6);
        graph.addVertex(DISTANT7);
        graph.addVertex(DISTANT8);
        graph.addVertex(X1);
        graph.addVertex(X2);
        graph.addVertex(X3);
        graph.addVertex(X4);
        graph.addVertex(X5);
        graph.addVertex(X6);
        graph.addVertex(X7);
        graph.addVertex(X8);
    }
}

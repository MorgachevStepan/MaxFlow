package com.stepanew.FirstTask;

import com.stepanew.utils.Constants;
import com.stepanew.utils.MyWeightedEdge;
import com.stepanew.utils.Render;
import com.stepanew.utils.Serialization;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 11.10.2023 17:17
 */
public class GraphCreator {
    private final String[] VERTEX = {"X1", "X2", "X3", "X4", "X5"
            , "X6", "X7", "X8", "X9", "X10"};

    private final Serialization serialization;
    private final Render render;
    private final String GRAPH_TXT;

    public GraphCreator(Serialization serialization, Render render, String graphTXT) {
        this.serialization = serialization;
        this.render = render;
        this.GRAPH_TXT = graphTXT;
    }

    public Graph<String, MyWeightedEdge>CreateGraph() {
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

    private boolean isExist() {
        File file = new File(GRAPH_TXT);
        return file.exists();
    }

    private void CreateEdge(Graph<String, MyWeightedEdge> graph) {
        List<MyWeightedEdge> edgeList = new ArrayList<>();

        for (int i = 0; i < VERTEX.length / 2; i++){
            edgeList.add(graph.addEdge(Constants.SOURCE, VERTEX[i]));
        }

        for(int i = 0; i < VERTEX.length / 2; i++){
            for (int j = VERTEX.length / 2; j < VERTEX.length; j++){
                edgeList.add(graph.addEdge(VERTEX[i], VERTEX[j]));
            }
        }

        for(int i = VERTEX.length / 2; i < VERTEX.length; i++){
            edgeList.add(graph.addEdge(VERTEX[i], Constants.DISTANT));
        }

        GeneratedWeight(edgeList, graph);
    }

    private void GeneratedWeight(List<MyWeightedEdge> edgeList, Graph<String, MyWeightedEdge> graph) {
        for(MyWeightedEdge edge: edgeList){
            graph.setEdgeWeight(edge, (int)(Math.random() * 10 + 1));
        }
    }

    private void CreateVertex(Graph<String, MyWeightedEdge> graph) {
        graph.addVertex(Constants.SOURCE);
        graph.addVertex(Constants.DISTANT);

        for(String v: VERTEX){
            graph.addVertex(v);
        }
    }
}

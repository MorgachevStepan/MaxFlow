package com.stepanew.SecondTask;

import com.stepanew.FirstTask.MyWeightedEdge;
import com.stepanew.FirstTask.Render;
import com.stepanew.FirstTask.Serialization;
import com.stepanew.utils.Constants;
import org.jgrapht.Graph;

/**
 * @author Stepan Morgachev
 * @date 21.12.2023 13:47
 */
public class Main {
    public static void main(String[] args) {
        Graph<String, MyWeightedEdge> graph;
        Render render = new Render(Constants.GRAPH_PNG_SECOND);
        Serialization serialization = new Serialization(Constants.GRAPH_TXT_SECOND);
        GraphCreator graphCreator = new GraphCreator(serialization, render, Constants.GRAPH_TXT_SECOND);
        graph = graphCreator.CreateGraph();
    }
}

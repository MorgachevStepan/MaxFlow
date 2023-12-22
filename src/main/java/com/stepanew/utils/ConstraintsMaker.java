package com.stepanew.utils;

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

    public ConstraintsMaker(BufferedWriter writer, Graph<String, MyWeightedEdge> graph) {
        this.writer = writer;
        this.graph = graph;
    }

    public void makeObjectiveFunction() throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("max: ");
        for (MyWeightedEdge edge : graph.edgesOf(Constants.SOURCE)) {
            result.append("+X_").append(Constants.SOURCE).append("_").append(graph.getEdgeTarget(edge));
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

    public void makeCompositeConstraints() throws IOException{
        StringBuilder result = null;

        for(String vertex: graph.vertexSet()){
            StringBuilder leftPart = null;
            StringBuilder rightPart = null;

            for(MyWeightedEdge edge: graph.incomingEdgesOf(vertex)){
                if(leftPart == null){
                    leftPart = new StringBuilder();
                }
                leftPart.append("+X_").append(graph.getEdgeSource(edge)).append("_").append(vertex);
            }
            for(MyWeightedEdge edge: graph.outgoingEdgesOf(vertex)){
                if(rightPart == null){
                    rightPart = new StringBuilder();
                }
                rightPart.append("+X_").append(vertex).append("_").append(graph.getEdgeTarget(edge));
            }

            if(rightPart != null && leftPart != null){
                result = new StringBuilder(leftPart + "=" + rightPart + ";\n");
                String buffer = result.toString();
                writer.write(buffer.replace("=+", "="));
            }
        }

        writer.write("\n");
    }
}

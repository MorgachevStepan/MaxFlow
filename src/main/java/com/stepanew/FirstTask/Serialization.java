package com.stepanew.FirstTask;

import org.jgrapht.Graph;

import java.io.*;

/**
 * @author Stepan Morgachev
 * @date 17.10.2023 18:10
 */
public class Serialization {
    private final String GRAPH_TXT = "src/main/resources/graph.txt";

    public void Serialize(Graph<String, MyWeightedEdge> graph){
        FileOutputStream outputStream;
        try{
            outputStream = new FileOutputStream(GRAPH_TXT);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(graph);
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Graph<String, MyWeightedEdge> Deserialize(){
        Graph<String, MyWeightedEdge> graph = null;
        try {
            FileInputStream inputStream = new FileInputStream(GRAPH_TXT);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            graph = (Graph<String, MyWeightedEdge>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return graph;
    }
}

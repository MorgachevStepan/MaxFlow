import org.jgrapht.Graph;

import java.io.*;

/**
 * @author Stepan Morgachev
 * @date 17.10.2023 18:10
 */
public class Serialization {
    public void Serialize(Graph<String, MyWeightedEdge> graph){
        FileOutputStream outputStream;
        try{
            outputStream = new FileOutputStream("graph.txt");
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
            FileInputStream inputStream = new FileInputStream("graph.txt");
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

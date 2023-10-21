import lpsolve.LpSolve;
import lpsolve.LpSolveException;
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
            ConstraintsMaker constraintsMaker = new ConstraintsMaker(writer, graph);
            constraintsMaker.makeObjectiveFunction();
            constraintsMaker.makeCompositeConstraints();
            constraintsMaker.makeSimpleConstraints();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        try {
            LpSolve lpSolve = LpSolve.readLp("graph.lp", LpSolve.NORMAL, "MyLPModel");
            lpSolve.solve();
            System.out.println(lpSolve.getObjective());
            lpSolve.deleteLp();
        } catch (LpSolveException e) {
            throw new RuntimeException(e);
        }
    }
}

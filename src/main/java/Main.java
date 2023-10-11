import lpsolve.*;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 02.10.2023 11:47
 */
public class Main {
    public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        GraphCreator graphCreator = new GraphCreator();
        graphCreator.CreateGraph(graph);

        //JGraphXAdapter


        /*try {
            LpSolve solver = LpSolve.makeLp(0, 4);

            solver.strAddConstraint("3 2 2 1", LpSolve.LE, 4);
            solver.strAddConstraint("0 4 3 1", LpSolve.GE, 3);

            solver.strSetObjFn("2 3 -2 3");

            solver.solve();

            System.out.println("Value of objective function: " + solver.getObjective());

            double[] vars = solver.getPtrVariables();
            for(double var: vars)
                System.out.println(var);

            solver.deleteLp();

        } catch (LpSolveException e) {
            throw new RuntimeException(e);
        }*/
    }
}

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * @author Stepan Morgachev
 * @date 02.10.2023 11:47
 */
public class Main {

    public static void main(String[] args){
        Graph<String, MyWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(MyWeightedEdge.class);
        Render render = new Render();
        Serialization serialization = new Serialization();
        GraphCreator graphCreator = new GraphCreator(serialization);
        graphCreator.CreateGraph(graph);



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

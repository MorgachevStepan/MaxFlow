import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import lpsolve.*;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stepan Morgachev
 * @date 02.10.2023 11:47
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Graph<String, MyWeightedEdge> graph = new SimpleWeightedGraph<>(MyWeightedEdge.class);
        GraphCreator graphCreator = new GraphCreator();
        graphCreator.CreateGraph(graph);

        JGraphXAdapter graphXAdapter = new JGraphXAdapter(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphXAdapter);
        layout.execute(graphXAdapter.getDefaultParent());

        BufferedImage image = mxCellRenderer.createBufferedImage(graphXAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File("graph.png");
        ImageIO.write(image, "PNG", imgFile);


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

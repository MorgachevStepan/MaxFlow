package com.stepanew.SecondTask;

import com.stepanew.utils.ConstraintsMaker;
import com.stepanew.utils.MyWeightedEdge;
import com.stepanew.utils.Render;
import com.stepanew.utils.Serialization;
import com.stepanew.utils.Constants;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import org.jgrapht.Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        graphCreator.completeGraph(graph);
        render.RenderGraph(graph, "p.png");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.GRAPH_LP_SECOND))) {
            ConstraintsMaker constraintsMaker = new ConstraintsMaker(writer, graph);
            constraintsMaker.makeObjectiveFunction();
            constraintsMaker.makeCompositeConstraints();
            constraintsMaker.makeSimpleConstraints();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        try {
            LpSolve lpSolve = LpSolve.readLp(Constants.GRAPH_LP_SECOND, LpSolve.NORMAL, "MyLPModel");
            lpSolve.solve();
            System.out.println(lpSolve.getObjective());
            lpSolve.deleteLp();
        } catch (LpSolveException e) {
            throw new RuntimeException(e);
        }
    }
}

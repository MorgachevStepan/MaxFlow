package com.stepanew.FirstTask;

import com.stepanew.utils.Constants;
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
        Render render = new Render(Constants.GRAPH_PNG_FIRST);
        Serialization serialization = new Serialization(Constants.GRAPH_TXT_FIRST);
        GraphCreator graphCreator = new GraphCreator(serialization, render, Constants.GRAPH_TXT_FIRST);
        graph = graphCreator.CreateGraph();


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.GRAPH_LP_FIRST))) {
            ConstraintsMaker constraintsMaker = new ConstraintsMaker(writer, graph);
            constraintsMaker.makeObjectiveFunction();
            constraintsMaker.makeCompositeConstraints();
            constraintsMaker.makeSimpleConstraints();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

        try {
            LpSolve lpSolve = LpSolve.readLp(Constants.GRAPH_LP_FIRST, LpSolve.NORMAL, "MyLPModel");
            lpSolve.solve();
            System.out.println(lpSolve.getObjective());
            lpSolve.deleteLp();
        } catch (LpSolveException e) {
            throw new RuntimeException(e);
        }
    }
}

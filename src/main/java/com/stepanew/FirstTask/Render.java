package com.stepanew.FirstTask;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Stepan Morgachev
 * @date 17.10.2023 18:05
 */
public class Render {
    private final String GRAPH_PNG;

    public Render(String graphPNG){
        this.GRAPH_PNG = graphPNG;
    }

    public void RenderGraph(Graph<String, MyWeightedEdge> graph){
        JGraphXAdapter graphXAdapter = new JGraphXAdapter(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphXAdapter);
        layout.execute(graphXAdapter.getDefaultParent());

        BufferedImage image = mxCellRenderer.createBufferedImage(graphXAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File(GRAPH_PNG);
        try {
            ImageIO.write(image, "PNG", imgFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}

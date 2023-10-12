import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.Serializable;

/**
 * @author Stepan Morgachev
 * @date 12.10.2023 12:08
 */
public class MyWeightedEdge extends DefaultWeightedEdge implements Serializable {
    @Override
    public String toString() {
        return Double.toString(getWeight());
    }
}

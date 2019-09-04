package apibasedimport;

import java.util.HashMap;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.DefaultMapDataHandler;
import de.westnordost.osmapi.map.handler.MapDataHandler;



public class ParsingMapDataHandler extends DefaultMapDataHandler implements MapDataHandler {

	private Graph<ImportedNode, ImportedEdge> g = new SimpleWeightedGraph<>(ImportedEdge.class);
	private HashMap<Long,ImportedNode> myNodes = new HashMap<Long, ImportedNode>();
	
	@Override
	public void handle(BoundingBox bounds) {
		System.out.println("Bounding box: nothing to do");
		super.handle(bounds);
	}

	@Override
	public void handle(Node node) {
		
		
		/*if(node.getTags()!=null) {
			System.out.println("Got tagged Node: "+node+" with ID:"+node.getId());
			for(String tag: node.getTags().keySet())
			System.out.println("Tag:"+tag+",value:"+node.getTags().get(tag));
		}*/
		super.handle(node);
		ImportedNode n = new ImportedNode(node);
		g.addVertex(n);
		myNodes.put(node.getId(), n);
		
		
	}

	@Override
	public void handle(Way way) {
		System.out.println("Got Way: "+way+" with ID:"+way.getId());
		super.handle(way);
		long previousNodeID = -1;
		for(long nodeID: way.getNodeIds()) {
			if(previousNodeID==-1) {
				previousNodeID = nodeID;
				continue;
			}	 
			g.addEdge(myNodes.get(previousNodeID), myNodes.get(nodeID));
			previousNodeID = nodeID;
		}
		
	}

	
	@Override
	public void handle(Relation relation) {
		//System.out.println("Got Relation: "+relation+" with ID:"+relation.getId());
		super.handle(relation);
	}
	
	public Graph<ImportedNode, ImportedEdge> getGraph() {
		return g;
	}

}

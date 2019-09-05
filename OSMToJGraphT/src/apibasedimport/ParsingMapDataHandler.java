package apibasedimport;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.SimpleWeightedGraph;

import de.westnordost.osmapi.map.MapDataDao;
import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.DefaultMapDataHandler;
import de.westnordost.osmapi.map.handler.MapDataHandler;



public class ParsingMapDataHandler extends DefaultMapDataHandler implements MapDataHandler {

	
	private Graph<Long, ImportedEdge> g = new DirectedPseudograph<>(ImportedEdge.class);
	private HashMap<Long,Node> myNodes = new HashMap<Long, Node>();
	
	
	public  HashMap<Long, Node> getNodesMap() {
		return this.myNodes;
	}
	
	
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
		//ImportedNode n = new ImportedNode(node.getId());
		g.addVertex(node.getId());
		myNodes.put(node.getId(), node);
		
		
	}

	@Override
	public void handle(Way way) {
		super.handle(way);
		System.out.println("Got Way: "+way+" with ID:"+way.getId());
		long previousNodeID = -1;
		for(long nodeID: way.getNodeIds()) {
			if(previousNodeID==-1) {
				previousNodeID = nodeID;
				continue;
			}	 
			if(!myNodes.containsKey(previousNodeID)) {
				System.out.println("previously unencountered node found in a way");
				myNodes.put(previousNodeID, null);
			}
			if(!myNodes.containsKey(nodeID)) {
				System.out.println("previously unencountered node found in a way");
				myNodes.put(nodeID, null);
			}
			System.out.println("Adding edge from node ID:"+previousNodeID+" to:"+nodeID);
			ImportedEdge edge = new ImportedEdge(previousNodeID, nodeID);
			g.addEdge(previousNodeID, nodeID, edge);
			//System.out.println("Node 1 is:"+myNodes.get(previousNodeID));
			//System.out.println("Node 2 is:"+myNodes.get(nodeID));
			//System.out.println("Adding edge from node:"+myNodes.get(previousNodeID)+" to:"+myNodes.get(nodeID));

			previousNodeID = nodeID;
		}
		
	}

	
	@Override
	public void handle(Relation relation) {
		//System.out.println("Got Relation: "+relation+" with ID:"+relation.getId());
		super.handle(relation);
	}
	
	public Graph<Long, ImportedEdge> getGraph() {
		return g;
	}

}

package apibasedimport;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ComponentNameProvider;

import de.westnordost.osmapi.map.data.Node;
import utilities.Haversine;


public class ElementNameProvider<Object> implements ComponentNameProvider<Object> {
	
	private ParsingMapDataHandler nodesMapProvider;
	public ElementNameProvider(ParsingMapDataHandler nodesMapProvider) {
		super();
		this.nodesMapProvider = nodesMapProvider;
	}

	public String getName(Object component) {
		//TODO - reject is not R or C or null
		if(component==null)
			return "";
		if(component instanceof Long) {
			return ((Long)component).toString()+"";
		} else if (component instanceof ImportedEdge) {
			
			ImportedEdge edge = (ImportedEdge)component;

			
			Node n1 = nodesMapProvider.getNodesMap().get(edge.sourceNode);
			Node n2 = nodesMapProvider.getNodesMap().get(edge.targetNode);
			
			double dist = Haversine.distance(n1.getPosition().getLatitude(), 
					n1.getPosition().getLongitude(),
					n2.getPosition().getLatitude(), 
					n2.getPosition().getLongitude());
			
			
			dist *= 100000;
			dist = Math.round(dist);
			return (dist/100)+""; 
		}
		else 
			return component.toString();
		
	}
	
}

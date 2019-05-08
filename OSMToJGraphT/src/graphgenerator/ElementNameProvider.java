package graphgenerator;

import org.jgrapht.io.ComponentNameProvider;

public class ElementNameProvider<Object> implements ComponentNameProvider<Object> {
	public String getName(Object component) {
		//TODO - reject is not R or C or null
		if(component==null)
			return "";
		if(component instanceof OSMNode) {
			return ((OSMNode)component).getNodeID()+"";
		} else if (component instanceof OSMEdge) {
			double dist = ((OSMEdge) component).getDistance();
			dist *= 100000;
			dist = Math.round(dist);
			return (dist/100)+"";
		}
		else 
			return component.toString();
		
	}
	
}

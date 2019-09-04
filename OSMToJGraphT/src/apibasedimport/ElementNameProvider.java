package apibasedimport;

import org.jgrapht.io.ComponentNameProvider;

import graphgenerator.OSMEdge;
import graphgenerator.OSMNode;

public class ElementNameProvider<Object> implements ComponentNameProvider<Object> {
	public String getName(Object component) {
		//TODO - reject is not R or C or null
		if(component==null)
			return "";
		if(component instanceof ImportedNode) {
			return ((ImportedNode)component).getNode().getId()+"";
		} else if (component instanceof ImportedEdge) {
			double dist = ((ImportedEdge) component).getDistance();
			dist *= 100000;
			dist = Math.round(dist);
			return (dist/100)+""; 
		}
		else 
			return component.toString();
		
	}
	
}

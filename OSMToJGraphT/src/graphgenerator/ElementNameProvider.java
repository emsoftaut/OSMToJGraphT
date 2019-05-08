package graphgenerator;

import org.jgrapht.io.ComponentNameProvider;

public class ElementNameProvider<Object> implements ComponentNameProvider<Object> {
	public String getName(Object component) {
		//TODO - reject is not R or C or null
		if(component!=null)
			return component.toString();
		return "";
	}
	
}

package apibasedimport;

import de.westnordost.osmapi.map.data.*;

public class ImportedNode {
	
	
	private Node node;

		
	public ImportedNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return this.node;
	}
	
	
    @Override
    public String toString()
    {
        return this.node+"";
    }
}

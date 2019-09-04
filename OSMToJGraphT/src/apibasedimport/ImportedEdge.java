package apibasedimport;



import org.jgrapht.graph.DefaultEdge;
import de.westnordost.osmapi.map.data.*;
import utilities.Haversine;

public class ImportedEdge extends DefaultEdge{
	
	private static final long serialVersionUID = 1L;
	
	ImportedNode sourceNode, targetNode;
	//Double distance = null;
	

	
	public ImportedEdge(ImportedNode source, ImportedNode target) {
		this.sourceNode = source;
		this.targetNode = target;
		//this.distance = calculateDistance(source, target);		
	}
	
	public ImportedNode getSourceNode() {
		return this.sourceNode;
	}
	
	public void setSourceNode(ImportedNode source) {
		this.sourceNode = source;
	}
	
	public ImportedNode getTargetNode() {
		return this.targetNode; 
	}
	
	public void setTargetNode(ImportedNode target) {
		this.targetNode = target;
	}
	
	public ImportedNode[] getBothNodes() {
		ImportedNode[]  nodes = {this.sourceNode, this.targetNode};
		return nodes; 
	}
	
	public ImportedNode getNeighbour(ImportedNode v) {
		if (v == this.sourceNode ) {
			return this.targetNode; 
		} else if (v == this.targetNode){
			return this.sourceNode;
		} else {
			return null;
		}
	}
	
	public Double calculateDistance() {
		return Haversine.distance(this.sourceNode.getNode().getPosition().getLatitude(), this.sourceNode.getNode().getPosition().getLongitude(),
				this.targetNode.getNode().getPosition().getLatitude(), this.targetNode.getNode().getPosition().getLongitude());
	}
	
	public Double getDistance() {
		return this.calculateDistance();
	}
	
    @Override
    public String toString()
    {
        return this.sourceNode + "->" + this.targetNode;
    }
}

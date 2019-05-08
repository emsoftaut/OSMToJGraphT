package graphgenerator;



import org.jgrapht.graph.DefaultEdge;

public class OSMEdge extends DefaultEdge{
	
	private static final long serialVersionUID = 1L;
	
	OSMNode sourceNode, targetNode;
	Double distance = null;
	

	
	public OSMEdge(OSMNode source, OSMNode target) {

		this.sourceNode = source;
		this.targetNode = target;
		this.distance = calculateDistance(source, target);		
	}
	
	public OSMNode getSourceNode() {
		return this.sourceNode;
	}
	
	public void setSourceNode(OSMNode source) {
		this.sourceNode = source;
	}
	
	public OSMNode getTargetNode() {
		return this.targetNode; 
	}
	
	public void setTargetNode(OSMNode target) {
		this.targetNode = target;
	}
	
	public OSMNode[] getBothNodes() {
		OSMNode[]  nodes = {this.sourceNode, this.targetNode};
		return nodes; 
	}
	
	public OSMNode getNeighbour(OSMNode v) {
		if (v == this.sourceNode ) {
			return this.targetNode; 
		} else if (v == this.targetNode){
			return this.sourceNode;
		} else {
			return null;
		}
	}
	
	public Double calculateDistance(OSMNode source, OSMNode target) {
		return Haversine.distance(Double.parseDouble(source.getLat()), Double.parseDouble(source.getLon()),
				Double.parseDouble(target.getLat()), Double.parseDouble(target.getLon()));
	}
	
	public Double getDistance() {
		return this.distance;
	}
	
    @Override
    public String toString()
    {
        return this.sourceNode + "->" + this.targetNode;
    }
}

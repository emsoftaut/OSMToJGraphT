package apibasedimport;



import org.jgrapht.graph.DefaultEdge;
import de.westnordost.osmapi.map.data.*;
import utilities.Haversine;

public class ImportedEdge extends DefaultEdge{
	
	private static final long serialVersionUID = 1L;

	
	Long sourceNode, targetNode;
	//Double distance = null;
	

	
	public ImportedEdge(Long source, Long target) {
		this.sourceNode = source;
		this.targetNode = target;
		//this.distance = calculateDistance(source, target);		
	}
	
	public Object getSource() {
		return this.sourceNode;
	}
	

	
	public Object getTarget() {
		return this.targetNode; 
	}
	
    @Override
    public String toString()
    {
        return this.sourceNode + "->" + this.targetNode;
    }
}

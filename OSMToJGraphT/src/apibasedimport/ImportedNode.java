package apibasedimport;

import de.westnordost.osmapi.map.MapDataDao;
import de.westnordost.osmapi.map.data.*;

public class ImportedNode {
	
	private long nodeID;
	//private static MapDataDao mapDao = null;
	

	//public static void setMapDataDao(MapDataDao mapDao) {
		//if(ImportedNode.mapDao==null)
		//	ImportedNode.mapDao = mapDao;
		//else
		//	throw new RuntimeException("ImportedNode:Pl don't change the mapdao midway through the session!");
	//}
	public ImportedNode(Long nodeId) {
		this.nodeID = nodeId;
	}

	//public Node getNode() {
	//	return ImportedNode.mapDao.getNode(this.nodeID);
	//}
	
	public long getID() {
		return this.nodeID;
	}
		
    @Override
    public String toString()
    {
        return this.nodeID+"";
    }
    
    
}

package graphgenerator;
import java.util.ArrayList;


public class OSMNode {
	
	private long nodeID;
	private String lat;
	private String lon;
	private String name;
		
	public OSMNode(long passedNodeID) {
		this.nodeID = passedNodeID;
	}

	public OSMNode(long passedNodeID, String passedLat, String passedLon, String name) {
		this.nodeID = passedNodeID;
		this.lat = passedLat;
		this.lon = passedLon;
		this.name = name;
	}
		

	
	/*
	 * 		METHODS
	 */
	

	public void setNodeID(long passedNodeID) {
		this.nodeID = passedNodeID;
	}//End setNodeID
	
	public long getNodeID() {
		return this.nodeID;
	}//End getNodeID
	
	public void setLat(String passedLat) {
		this.lat = passedLat;
	}//End setLat
	
	public String getLat() {
		return this.lat;
	}//End getNodeID
	
	public void setLon(String passedLon) {
		this.lon = passedLon;
	}
	
	public String getLon() {
		return this.lon;
	}
	
	public String getName() {
		return this.name;
	}
	
    @Override
    public String toString()
    {
        return this.nodeID+"";
    }//End toString
}//End OSMNode

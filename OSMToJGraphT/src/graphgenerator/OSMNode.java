package graphgenerator;
import java.util.ArrayList;


public class OSMNode {
	
	private long nodeID;
	private String lat;
	private String lon;

	

	
	

	
	public OSMNode(long passedNodeID) {
		this.nodeID = passedNodeID;
		//this.visited = false;
	}//End passedOSMNode
	

	public OSMNode(long passedNodeID, String passedLat, String passedLon) {
		this.nodeID = passedNodeID;
		this.lat = passedLat;
		this.lon = passedLon;
		//this.visited = false;
	}//End OSMNode Constructor
	
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
	}//End setLon
	
	public String getLon() {
		return this.lon;
	}//End getNodeID

	
    @Override
    public String toString()
    {
        return "(" + this.nodeID + ")";
    }//End toString
}//End OSMNode

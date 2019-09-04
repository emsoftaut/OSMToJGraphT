package apibasedimport;

import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.map.MapDataDao;
import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.LatLon;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.DefaultMapDataHandler;
import de.westnordost.osmapi.map.handler.MapDataHandler;

public class APIBasedImport {
	
	public static void main(String[] args) {
		OsmConnection osm = new OsmConnection(
                "https://api.openstreetmap.org/api/0.6/",
                "my user agent", null);
		MapDataDao mapDao = new MapDataDao(osm);
		

		BoundingBox boundingBox = new BoundingBox(-36.84883, 174.75937, -36.84326, 174.77107);
		
		
		ParsingMapDataHandler dataHandler = new ParsingMapDataHandler();
		
		mapDao.getMap(boundingBox, dataHandler);
		
		System.out.println("made it here");
		
		Node n = mapDao.getNode(8721668);
		if(n!=null) 
			System.out.println("Node's tags:"+n.getTags());
		else
			System.out.println("Node is null");
		
		
		//TODO
		//mapDao.getNode(123L).getTags();
		//mapDao.getNodes(null);
		//mapDao.getNode(123L);
		
	}

}

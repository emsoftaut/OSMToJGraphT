package graphgenerator;
import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;


//Feedback from EMSOFT
// do not use magic numbers and also strings
// avoid static methods!!!
// efficiency - multicore/threaded
// naming conventions
// Java - great for prototyping (efficiency wise - may be C/C++)
// write own print function (to change verbosity)
// use .equals to compare strings (but what if first string is null)
// use comments


public class OSMToJGraphT {
	

	public static void main(String[] args) {
		try {
			File inputFile = new File("resources/aklreallysmall.osm"); 
	        SAXReader reader = new SAXReader();
			Document document = reader.read(inputFile); 
			List<Node> xmlNodes = document.selectNodes("/osm/node");
			HashMap<Long,OSMNode> idToNodeMap = new HashMap<Long, OSMNode>();
			parseNodes(xmlNodes,idToNodeMap);
			Graph<OSMNode, OSMEdge> g = new SimpleWeightedGraph<OSMNode, OSMEdge>(OSMEdge.class);
			List<Node> ways = document.selectNodes("/osm/way");
			parseWays(ways, idToNodeMap, g);
			OSMGraphToDOT.exportOSMGraphToFile(g,"./resources/dotoutput.gv");
			 
			
			DijkstraShortestPath<OSMNode, OSMEdge> shortestPath = new DijkstraShortestPath<OSMNode, OSMEdge>(g);
			SingleSourcePaths<OSMNode, OSMEdge> iPaths = shortestPath.getPaths(idToNodeMap.get(5183768449L));
			System.out.println(iPaths.getPath(idToNodeMap.get(4297243964L)) + "\n");

		} catch (DocumentException ex) {
			ex.printStackTrace(); 
		}
	}

	private static HashSet<Node> parseNodes(List<Node> xmlNodes, HashMap<Long,OSMNode> idToNodeMap) {
		OSMNode newNode;
		for(Node node: xmlNodes) {
				long id = Long.parseLong(node.valueOf("@id"));
				newNode = new OSMNode(id, 
						node.valueOf("@lat"),
						node.valueOf("@lon"));
				if(id==317655386) {
					if(node.hasContent()) {
						List<Node> nodes = node.selectNodes("*");
						for(Node n: nodes) {
							String key = 
							
							System.out.println("Printing:"+n);
							System.out.println("Printing:"+n.getName());
							System.out.println("Value is:"+n.valueOf("@*"));
							if((n.valueOf("@*")+"").equals("name")) {
								List<Node> nodes2 = n.selectNodes("*");
								for(Node n2: nodes2) {
									System.out.println("Printing:"+n2);
									System.out.println("Printing:"+n2.getName());
									System.out.println("Value is:"+n2.valueOf("@*"));
								}
							}

						}
						//System.out.println(nodes);
					}
					System.out.println("Node ID is:"+id);
				}
				idToNodeMap.put(id, newNode);
		}
		return null;
	}

	private static void parseWays(List<Node> ways, HashMap<Long,OSMNode> idToNodeMap, Graph<OSMNode, OSMEdge> g) {
		System.out.println("Found "+ways.size()+" ways");

		
		//add all the nodes to the graph first		
		for(long l: idToNodeMap.keySet()) {
			g.addVertex(idToNodeMap.get(l));
			System.out.println("Added node:"+l);
		}
			
		
		for (Node way : ways) {
			if (way.hasContent()) {
				//process each way
				long currentNodeID = 0;
				long previousNodeID=-1;	
	    		List<Node> nodes = way.selectNodes("*");	    		
	    		for (int i = 0; i < nodes.size(); i++) {
	        		Node currentXMLNode = nodes.get(i);
	        		
	        		if (currentXMLNode.getName() == "nd") {
	        			currentNodeID =  Long.parseLong(nodes.get(i).valueOf("@ref"));
	        			if(previousNodeID==-1) {
	        				previousNodeID = currentNodeID;
	        				continue;
	        			}	        			
	        			OSMNode previousNode = idToNodeMap.get(previousNodeID);   
	        			OSMNode currentNode = idToNodeMap.get(currentNodeID);
	        			OSMEdge edge = new OSMEdge(previousNode, currentNode);	        			
	        			g.addEdge(previousNode, currentNode, edge);
	        			System.out.println("Added edge:"+edge);
	        			previousNodeID = currentNodeID;
	        		}
	    		}
	    	}
        }			
	}


	

	

}

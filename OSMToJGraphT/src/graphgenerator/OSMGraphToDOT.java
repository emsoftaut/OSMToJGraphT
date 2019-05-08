package graphgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.DOTExporter;

public class OSMGraphToDOT {
	
	public static void exportOSMGraphToFile(Graph<OSMNode, OSMEdge> g, String fileName) {
		try{
			File outputFile = new File(fileName);
			if(!outputFile.exists())
				outputFile.createNewFile();
			ElementNameProvider objectToStringProvider = new ElementNameProvider();
			//EdgeLabelProvider eProvider = new EdgeLabelProvider();
			FileWriter fileWriter = new FileWriter(outputFile);
			DOTExporter<OSMNode, OSMEdge> dotExporter = new DOTExporter<OSMNode, OSMEdge>(objectToStringProvider, 
					objectToStringProvider,  
					objectToStringProvider);
			dotExporter.exportGraph(g, fileWriter);
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}

}

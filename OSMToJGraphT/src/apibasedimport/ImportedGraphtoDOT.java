package apibasedimport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.DefaultEdge;

public class ImportedGraphtoDOT {
	
	public static void exportOSMGraphToFile(Graph<Long, ImportedEdge> g, String fileName, ParsingMapDataHandler nodesMapProvider) {
		try{
			File outputFile = new File(fileName);
			if(!outputFile.exists())
				outputFile.createNewFile();
			ElementNameProvider objectToStringProvider = new ElementNameProvider(nodesMapProvider);
			//EdgeLabelProvider eProvider = new EdgeLabelProvider();
			FileWriter fileWriter = new FileWriter(outputFile);
			DOTExporter<Long, ImportedEdge> dotExporter = new DOTExporter<Long, ImportedEdge>(objectToStringProvider, 
					objectToStringProvider,  
					objectToStringProvider);
			dotExporter.exportGraph(g, fileWriter);

			
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}

}

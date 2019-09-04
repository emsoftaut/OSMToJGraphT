package apibasedimport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.alg.shortestpath.*;

public class ImportedGraphtoDOT {
	
	public static void exportOSMGraphToFile(Graph<ImportedNode, ImportedEdge> g, String fileName) {
		try{
			File outputFile = new File(fileName);
			if(!outputFile.exists())
				outputFile.createNewFile();
			ElementNameProvider objectToStringProvider = new ElementNameProvider();
			//EdgeLabelProvider eProvider = new EdgeLabelProvider();
			FileWriter fileWriter = new FileWriter(outputFile);
			DOTExporter<ImportedNode, ImportedEdge> dotExporter = new DOTExporter<ImportedNode, ImportedEdge>(objectToStringProvider, 
					objectToStringProvider,  
					objectToStringProvider);
			dotExporter.exportGraph(g, fileWriter);

			
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}

}

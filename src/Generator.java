package generator;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import cib.util.geo.Geo2D;
import net.sourceforge.gxl.GXLDocument;
import net.sourceforge.gxl.GXLEdge;
import net.sourceforge.gxl.GXLGraph;
import net.sourceforge.gxl.GXLNode;

public class Generator {
	
	ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	ArrayList<GXLEdge> edges = new ArrayList<GXLEdge>();
	float actualCenterX, actualCenterY;
	private Float cityBounds;

	public Generator(float x, float y, float r, int n, float rmin, float rmax, int nmin, int nmax) {
		this.actualCenterX = x + r;//TODO
		this.actualCenterY = y + r;//TODO
		this.generateNodes(x, y, r, n, rmin, rmax, nmin, nmax);
		this.setEdges();
		this.colorGraphs();
		GraphDrawer drawer = new GraphDrawer(cityBounds, nodes, edges);
		drawer.init();
		generateDocument();
	}

	/**
	 * Fills nodes list
	 * @return 
	 */
	private void generateNodes(float x, float y, float r, int n, float rmin, float rmax, int nmin, int nmax) {
		this.cityBounds = new Ellipse2D.Float(x, y, 2 * r, 2 * r);
//		Rectangle2D simplifiedCityBounds = new Rectangle((int) x, (int) y, (int) (2 * r), (int) (2 * r)); //TODO check if necessary
		float randomRadius, randomFrequenciesCount;
		
		for (int i = 0; i < n; i++) {
			randomRadius = randomFloat(rmin, rmax);
			randomFrequenciesCount = randomFloat(nmin, nmax);
			this.nodes.add(generateNode(i, cityBounds, randomRadius, randomFrequenciesCount));
		}
		
	};
	
	private CircleNode generateNode(int id, Ellipse2D circleBounds, float radius, float frequencies) {
		System.out.println("Generating node with ID: " + id);
		Point nodeCenter = new Point();
		do {
			nodeCenter.setLocation(randomDouble(circleBounds.getMinX(), circleBounds.getMaxX()), randomDouble(circleBounds.getMinY(), circleBounds.getMaxY()));
			
		} while (!circleBounds.contains(nodeCenter));	//set random node x and y while it is outside of city bounds
		
		return new CircleNode(String.valueOf(id), nodeCenter.x - radius, nodeCenter.y - radius, radius);
	}
	
	private void setEdges() {
		for (int i = 0; i < this.nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				try {
					Geo2D.intersection(nodes.get(j).getNodeCircleBody(), nodes.get(i).getNodeCircleBody()); //PICK UP
					edges.add(new GXLEdge(nodes.get(j), nodes.get(i)));
				} catch (Exception e) {
					continue;
				}
				
			}
		}
		//TODO
	};
	
	private float randomFloat(float min, float max){
		Random random = new Random();	
		return random.nextFloat() * (max - min) + min;
	}
	
	private double randomDouble(double min, double max){
		Random random = new Random();
		return (double) (random.nextFloat() * (max - min) + min);
	}
	
	private void colorGraphs() {
		for (int i = 0; i < this.nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				try {
					Geo2D.intersection(nodes.get(j).getNodeCircleBody(), nodes.get(i).getNodeCircleBody());
					nodes.get(j).increaseNeighbours();
				} catch (Exception e) {
					continue;
				}
			}	
		}
		
		int max = 0;
		for (int i = 0; i < this.nodes.size(); i++) {
			if (nodes.get(i).getNeighbours() > max) {
				max = nodes.get(i).getNeighbours(); 
			}
		}
		System.out.println("Liczba koniecznych czestotliwoci: " + max);
	}
	
	/**
	 * Creates GXL document with current graph structure
	 */
	private void generateDocument() {
		GXLDocument gxlDocument = new GXLDocument();
		GXLGraph graph = new GXLGraph("graph1");
		
		for (int i = 0; i < nodes.size(); i++) {
			GXLNode tempNode = nodes.get(i);
			graph.add(tempNode);
		}
		
		for (int i = 0; i < edges.size(); i++) {
			GXLEdge tempEdge = edges.get(i);
			graph.add(tempEdge);
		}
		
		gxlDocument.getDocumentElement().add(graph);
		// Write the document to file
		try {
		 gxlDocument.write(new File("document.gxl"));
		}
		catch (IOException ioe) {
		 System.out.println("Error while writing to file: " + ioe);
		}
	};
	
	

}

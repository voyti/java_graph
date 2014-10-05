package generator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sourceforge.gxl.GXLEdge;
import net.sourceforge.gxl.GXLNode;

public class GraphDrawer extends JFrame {
	
	ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	ArrayList<GXLEdge> edges = new ArrayList<GXLEdge>();
	private Ellipse2D cityBounds;
	
	public GraphDrawer(Ellipse2D cityBounds, ArrayList<CircleNode> nodes, ArrayList<GXLEdge> edges) {
		super("GraphDrawer");
		this.nodes = nodes;
		this.edges = edges;
		this.cityBounds = cityBounds;
	}
	
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final DrawingClass dc = new DrawingClass();
        JPanel canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                drawCity(dc, g2d, cityBounds);
				drawGraph(dc, g2d, nodes, edges);	// drawing nodes on the panel
                
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1000, 700);
            }
        };
        
        this.add(canvasPanel);

        this.pack();
        this.setVisible(true);
     }

	protected void drawCity(DrawingClass dc, Graphics2D g2d, Ellipse2D cityBounds) {
		dc.drawEmptyOval(g2d,(int) cityBounds.getX(), (int) cityBounds.getY(),  
				(int) cityBounds.getWidth(),  (int) cityBounds.getHeight());
	}

	protected void drawGraph(DrawingClass dc,Graphics2D g2d, ArrayList<CircleNode> nodes, ArrayList<GXLEdge> edges) {
		
		Ellipse2D currentNodeCircleBody;
		
		for (int i = 0; i < nodes.size(); i++) {
			currentNodeCircleBody = nodes.get(i).getNodeCircleBody();
			dc.drawEmptyOval(g2d, (int) currentNodeCircleBody.getX(), (int) currentNodeCircleBody.getY(),
        			(int) currentNodeCircleBody.getWidth() , (int) currentNodeCircleBody.getHeight());
			
			dc.drawColoredOvalWithAplha(g2d, (int) currentNodeCircleBody.getX(), (int) currentNodeCircleBody.getY(),
        			(int) currentNodeCircleBody.getWidth() , (int) currentNodeCircleBody.getHeight(), nodes.get(i).getNodeColor(), 0.50f);
			
			
        	dc.drawBlackOval(g2d, (int) currentNodeCircleBody.getCenterX(), (int) currentNodeCircleBody.getCenterY(),
        			2 , 2) ;
        }
	}
	
}

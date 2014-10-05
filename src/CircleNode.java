package generator;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import org.w3c.dom.Element;

import net.sourceforge.gxl.GXLNode;

/**
 * Circle node extends GXLNode to include information about radius and area.
 * It allows to easily detect circle node intersections. 
 * @author w.apanowicz (s375129)
 *
 */
class CircleNode extends GXLNode {
	
	private Ellipse2D nodeCircleBody;
	private int neighbours = 0;
	private Color nodeColor;
	
	public CircleNode(String id, float x, float y, float radius) {
		super(id);
		this.nodeCircleBody = new Ellipse2D.Float(x, y, radius * 2, radius * 2);
	}
	
	public boolean checkForIntersection (CircleNode otherNode) {
		if (otherNode.nodeCircleBody.intersects(this.nodeCircleBody.getX(), this.nodeCircleBody.getY(), this.nodeCircleBody.getWidth(),  this.nodeCircleBody.getHeight())) {
			return true;
		} else {
			return false;	
		}
	}

	public Ellipse2D getNodeCircleBody() {
		return nodeCircleBody;
	}

	public void setNodeCircleBody(Ellipse2D nodeCircleBody) {
		this.nodeCircleBody = nodeCircleBody;
	}

	public int getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
	
	public void increaseNeighbours() {
		this.neighbours++;
	}


	public Color getNodeColor() {
		Color[] colors = {
				new Color(255,182,193),
				new Color(221,160,221),
				new Color(138,43,226),
				new Color(0,0,128),
				new Color(135,206,250),
				new Color(0,255,255),
				new Color(0,250,154),
				new Color(0,255,0),
				new Color(255,255,240),
				new Color(255,215,0),
				new Color(255,235,205),
				new Color(244,164,96),
				new Color(210,105,30),
				new Color(139,69,19),
				new Color(255,245,238),
				new Color(160,82,45),
				new Color(255,160,122),
				new Color(255,127,80),
				new Color(255,69,0),
				new Color(233,150,122),
				new Color(255,99,71),
				new Color(250,128,114),
				new Color(128,0,0)};
		
		return colors[neighbours];
	}

	

}

package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JSlider;

public class Tetrahedron {
	private int x;
	private int y;
	private int z;
	private Graphics2D g2;
	private JSlider headingSlider;
	private JSlider pitchSlider;
	

	public Tetrahedron(int x, int y, int z, Graphics2D g2, JSlider headingSlider, JSlider pitchSlider) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.g2 = g2;
		this.headingSlider = headingSlider;
		this.pitchSlider = pitchSlider;
	}
	
	public ArrayList<Triangle> wireFrame() {
		ArrayList<Triangle> tris = new ArrayList<>();
		tris.add(new Triangle(new Vertex(x,y,z),
				new Vertex(-x,-y,z),
				new Vertex(-x,y,-z),
				Color.white
				));
		tris.add(new Triangle(new Vertex(x,y,z),
				new Vertex(-x,-y,z),
				new Vertex(x,-y,-z),
				Color.white
				));
		tris.add(new Triangle(new Vertex(-x,y,-z),
				new Vertex(x,-y,-z),
				new Vertex(x,y,z),
				Color.white
				));
		tris.add(new Triangle(new Vertex(-x,y,-z),
				new Vertex(x,-y,-z),
				new Vertex(-x,-y,z),
				Color.white
				));
		return tris;
	}
	
	
	public void render() {
		
		ArrayList<Triangle> tris = wireFrame();
		
		
		double heading = Math.toRadians(headingSlider.getValue());
		Matrix3 headingTransform = new Matrix3(new double[] {
		        Math.cos(heading), 0, Math.sin(heading),
		        0, 1, 0,
		        -Math.sin(heading), 0, Math.cos(heading)
		    });
		double pitch = Math.toRadians(pitchSlider.getValue());
		Matrix3 pitchTransform = new Matrix3(new double[] {
		        1, 0, 0,
		        0, Math.cos(pitch), Math.sin(pitch),
		        0, -Math.sin(pitch), Math.cos(pitch)
		    });
		Matrix3 transform = headingTransform.multiply(pitchTransform);
		

			
		for(Triangle t : tris) {
			Path2D path = new Path2D.Double();
			Vertex v1 = transform.transform(t.v1, transform);
		    Vertex v2 = transform.transform(t.v2, transform);
		    Vertex v3 = transform.transform(t.v3, transform);
			path.moveTo(v1.getX(),v1.getY());
			path.lineTo(v2.getX(), v2.getY());
			path.lineTo(v3.getX(), v3.getY());
			path.closePath();
			g2.draw(path);
		}
	}
	
	
}

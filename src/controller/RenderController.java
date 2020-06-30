package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import view.*;

public class RenderController {
	private JFrame frame;
	private Container pane;
	private JSlider headingSlider;
	private JSlider pitchSlider;
	private JSlider renderPanel;
	private Graphics2D g2;
	
	public RenderController() {
		//Initalize frame
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		JSlider headingSlider = new JSlider(0, 360, 180);
		pane.add(headingSlider, BorderLayout.SOUTH);
		
		JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
		pane.add(pitchSlider, BorderLayout.EAST);
		
		JPanel renderPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLACK);
				g2.fillRect(0, 0, getWidth(), getHeight());
				
				g2.translate(getWidth()/2, getHeight()/2);
				g2.setColor(Color.white);
				
			
				Tetrahedron tetrahedron = new Tetrahedron(100, 100, 100, g2, headingSlider, pitchSlider);
				tetrahedron.render();
				
			}
		};
		headingSlider.addChangeListener(e -> renderPanel.repaint());
		pitchSlider.addChangeListener(e -> renderPanel.repaint());
		pane.add(renderPanel, BorderLayout.CENTER);
		
		frame.setSize(400, 400);
		frame.setVisible(true);
	
	}
	
	
	
	
	
}

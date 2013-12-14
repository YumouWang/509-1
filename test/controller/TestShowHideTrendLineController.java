package controller;

import static org.junit.Assert.*;

import model.CartesianPlot;
import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestShowHideTrendLineController {

	
	Model m;
	MainGUI mainGUI;
	ShowHideTrendLineController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new ShowHideTrendLineController(m, mainGUI);
		mainGUI.setVisible(true);
	}
		
	@Test
	public void testShowTrendLine() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		CartesianPlot g = new CartesianPlot();
		m.switchGraph(g);
		
		g.setTrendLineVisible(false);
		g.setFormulaVisible(false);
		
		assertTrue(controller.act());
		
		assertTrue(g.isTrendLineVisible());
		assertTrue(g.isFormulaVisible());
	}
	
	@Test
	public void testHideTrendLine() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);

		CartesianPlot g = new CartesianPlot();
		m.switchGraph(g);

		g.setTrendLineVisible(true);
		g.setFormulaVisible(true);
		
		assertTrue(controller.act());
		assertFalse(g.isTrendLineVisible());
		assertFalse(g.isFormulaVisible());
	}
}

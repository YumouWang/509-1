package controller;

import static org.junit.Assert.*;

import model.CartesianPlot;
import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestShowHideFormulaController {

	Model m;
	MainGUI mainGUI;
	ShowHideTrendLineEquationController controller;
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new ShowHideTrendLineEquationController(m, mainGUI);
		mainGUI.setVisible(true);
	}
		
	@Test
	public void testShowFormula() {
		CartesianPlot g = new CartesianPlot();
		m.switchGraph(g);
		
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		ShowHideTrendLineEquationController controller = new ShowHideTrendLineEquationController(m, mainGUI);
		
		g.setTrendLineVisible(false);
		g.setFormulaVisible(false);
		
		assertTrue(controller.act());
		assertTrue(g.isFormulaVisible());
	}
	
	@Test
	public void testHideFormula() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		CartesianPlot g = new CartesianPlot();
		m.switchGraph(g);
		g.setTrendLineVisible(true);
		g.setFormulaVisible(true);
		assertTrue(controller.act());
		assertFalse(g.isFormulaVisible());
	}
}

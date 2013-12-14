package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ColumnChart;
import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestShowHideAxisValuesController {

	Model m;
	MainGUI mainGUI;
	ShowHideAxisLabelController controller;
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new ShowHideAxisLabelController(m, mainGUI);
		mainGUI.setVisible(true);
	}
	
	
	@Test
	public void testCartesianPlotShowAxisValues() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		m.getGraph().setAxisValueVisible(false);
		assertTrue(controller.act());
		assertTrue(m.getGraph().isAxisValueVisible());
	}
	
	@Test
	public void testColumnChartShowAxisValues() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		m.switchGraph(new ColumnChart());

		m.getGraph().setAxisValueVisible(false);
		assertTrue(controller.act());
		assertTrue(m.getGraph().isAxisValueVisible());
	}
	
	@Test
	public void testColumnChartHideAxisValues() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		m.switchGraph(new ColumnChart());
		
		m.getGraph().setAxisValueVisible(true);
		assertTrue(controller.act());
		assertFalse(m.getGraph().isAxisValueVisible());
	}
	
	@Test
	public void testCartesianPlotHideAxisValues() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		m.getGraph().setAxisValueVisible(true);
		assertTrue(controller.act());
		assertFalse(m.getGraph().isAxisValueVisible());
	}

}

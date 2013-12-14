package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ColumnChart;
import model.Model;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestShowHideBackgroundLinesController {

	Model m;
	MainGUI mainGUI;
	ShowHideHorizontalLinesController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new ShowHideHorizontalLinesController(m, mainGUI);
		mainGUI.setVisible(true);
	}
	
	@Test
	public void testCartesianPlotShowBackgroundLines() {
		m.getGraph().setBackgroundVisible(false);
		assertTrue(controller.act());
		assertTrue(m.getGraph().isBackgroundVisible());
	}
	
	@Test
	public void testCartesianPlotHideBackgroundLines() {
		m.getGraph().setBackgroundVisible(true);
		assertTrue(controller.act());
		assertFalse(m.getGraph().isBackgroundVisible());
	}
	
	@Test
	public void testColumnChartPlotShowBackgroundLines() {
		m.switchGraph(new ColumnChart());
		m.getGraph().setBackgroundVisible(false);
		assertTrue(controller.act());
		assertTrue(m.getGraph().isBackgroundVisible());
	}
	
	@Test
	public void testColumnChartHideBackgroundLines() {
		m.switchGraph(new ColumnChart());
		m.getGraph().setBackgroundVisible(true);
		assertTrue(controller.act());
		assertFalse(m.getGraph().isBackgroundVisible());
	}

}

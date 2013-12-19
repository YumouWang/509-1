package controller;

import static org.junit.Assert.*;

import model.Point;

import model.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataset.ICommonProperties;

import view.MainGUI;

public class TestSwitchGraphController {

	Model m;
	MainGUI mainGUI;
	SwitchGraphController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new SwitchGraphController(m, mainGUI);
	}
	
	@After
	public void tearDown() throws Exception {
		this.mainGUI.dispose();
	}
	
	@Test
	public void testSwitchGraphToCartesianPlot() {
		Point p1 = new Point(2000, 1);
		Point p2 = new Point(-2, 2);
		model.DataSet dataset = (model.DataSet)m.getDataSet();
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		
		assertTrue(controller.act());
		
		assertTrue(m.getGraphName().equals(ICommonProperties.cartesian));
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	@Test
	public void testSwitchGraphToColumnGraph() {
		Point p1 = new Point(2000, 1);
		Point p2 = new Point(-2, 2);
		model.DataSet dataset = (model.DataSet)m.getDataSet();
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		//mainGUI.rdbtnCartesianPlot.setSelected(true);
		//mainGUI.rdbtnColumnChart.setSelected(false);
		
		//assertTrue(controller.act());
		
		//assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		//assertFalse(mainGUI.btnShowHideFormula.isEnabled());

		//m.setGraph(ICommonProperties.cartesian);

		//m.properties.setProperty(ICommonProperties.trendLineVisible, Boolean.TRUE.toString());
		//m.properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.TRUE.toString());
		
		//mainGUI.rdbtnCartesianPlot.setSelected(false);
		mainGUI.rdbtnColumnGraph.setSelected(true);
		assertTrue(controller.act());
		
		assertTrue(m.getGraphName().equals(ICommonProperties.column));
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	@Test
	public void testSwitchGraphToMultipleLines() {
		Point p1 = new Point(20, 10);
		Point p2 = new Point(-200, 250);

		model.DataSet dataset = (model.DataSet)m.getDataSet();
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		mainGUI.rdbtnMultipleLines.setSelected(true);
	
		assertTrue(controller.act());
		assertTrue(m.getGraphName().equals(ICommonProperties.multipleLines));
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	@Test
	public void testSwitchGraphToHorizontalBarGraph() {
		Point p1 = new Point(20, 10);
		Point p2 = new Point(-200, 250);

		model.DataSet dataset = (model.DataSet)m.getDataSet();
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		mainGUI.rdbtnHorizontalBarGraph.setSelected(true);
		
		assertTrue(controller.act());
		assertTrue(m.getGraphName().equals(ICommonProperties.horizontalBarGraph));
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	@Test
	public void testOnePointSwitch() {
		Model m = new Model();
		MainGUI mainGUI = new MainGUI(m);
		mainGUI.setVisible(true);
		
		Point p1 = new Point(2000, 1);

		model.DataSet dataset = (model.DataSet)m.getDataSet();
		dataset.addPoint(p1);

		mainGUI.rdbtnColumnGraph.setSelected(true);
		
		SwitchGraphController controller = new SwitchGraphController(m, mainGUI);
		assertTrue(controller.act());
		
		assertTrue(m.getGraphName().equals(ICommonProperties.column));
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
			
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		assertTrue(controller.act());
		
		assertTrue(m.getGraphName().equals(ICommonProperties.cartesian));
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	


}

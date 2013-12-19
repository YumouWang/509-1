package controller;

import static org.junit.Assert.assertTrue;
import model.DataSet;
import model.Model;
import model.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.MainGUI;
import dataset.ICommonProperties;

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
	
	@After
	public void tearDown(){
		mainGUI.dispose();
	}
	
	
	@Test
	public void testCartesianPlotShowAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
		
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testCartesianPlotHideAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testColumnChartShowAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.column);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testColumnChartHideAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.column);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	

	@Test
	public void testMultipleLinesShowAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.multipleLines);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testMultipleLinesHideAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.multipleLines);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testHorizontalBarGraphShowAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.horizontalBarGraph);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	
	@Test
	public void testHorizontalBarGraphHideAxisValues() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(-1, -2);
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		m.setGraph(ICommonProperties.horizontalBarGraph);
		m.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.xAxisLabel)));
	}
	

}

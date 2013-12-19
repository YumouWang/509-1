package controller;

import static org.junit.Assert.assertTrue;
import model.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.MainGUI;
import dataset.ICommonProperties;

public class TestShowHideHorizontalLinesController {

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
	
	@After
	public void tearDown(){
		mainGUI.dispose();
	}

	
	@Test
	public void testCartesianPlotShowBackgroundLines() {
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testCartesianPlotHideBackgroundLines() {
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testColumnChartPlotShowBackgroundLines() {
		m.setGraph(ICommonProperties.column);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testColumnChartHideBackgroundLines() {
		m.setGraph(ICommonProperties.column);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testMultipleLinesPlotShowBackgroundLines() {
		m.setGraph(ICommonProperties.multipleLines);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testMultipleLinesPlotHideBackgroundLines() {
		m.setGraph(ICommonProperties.multipleLines);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testHorizontalBarGraphShowBackgroundLines() {
		m.setGraph(ICommonProperties.horizontalBarGraph);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.TRUE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
	@Test
	public void testHorizontalBarGraphHideBackgroundLines() {
		m.setGraph(ICommonProperties.horizontalBarGraph);
		m.properties.setProperty(ICommonProperties.horizontalLines, Boolean.TRUE.toString());
		assertTrue(controller.act());
		assertTrue(Boolean.FALSE.toString().equals(m.properties.getProperty(ICommonProperties.horizontalLines)));
	}
	
}

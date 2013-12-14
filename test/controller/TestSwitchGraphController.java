package controller;

import static org.junit.Assert.*;

import model.Point;

import model.CartesianPlot;
import model.ColumnChart;
import model.Model;

import org.junit.Before;
import org.junit.Test;

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
		mainGUI.setVisible(true);
	}
	
	@Test
	public void testSwitchGraphFromColumnChart() {
		Point p1 = new Point(2000, 1);
		Point p2 = new Point(-2, 2);

		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);

		m.switchGraph(new ColumnChart());
		
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		mainGUI.rdbtnColumnChart.setSelected(false);
		
		assertTrue(controller.act());
		
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());

		m.switchGraph(new CartesianPlot());

		((CartesianPlot)m.getGraph()).setTrendLineVisible(true);
		((CartesianPlot)m.getGraph()).setFormulaVisible(true);
		
		mainGUI.rdbtnCartesianPlot.setSelected(false);
		mainGUI.rdbtnColumnChart.setSelected(true);
		assertTrue(controller.act());
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	@Test
	public void testSwitchGraphFromCartesianPlot() {
		Point p1 = new Point(20, 10);
		Point p2 = new Point(-200, 250);

		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		m.switchGraph(new CartesianPlot());
	
		assertTrue(controller.act());
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());

		m.switchGraph(new ColumnChart());
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		mainGUI.rdbtnColumnChart.setSelected(false);
		
		mainGUI.rdbtnCartesianPlot.setSelected(false);
		mainGUI.rdbtnColumnChart.setSelected(true);
		
		assertTrue(controller.act());
		
		assertTrue(mainGUI.rdbtnColumnChart.isEnabled());
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertTrue(mainGUI.btnShowHideBackGroundLines.isEnabled());
	}
	
	@Test
	public void testOnePointSwitch() {
		Model m = new Model();
		MainGUI mainGUI = new MainGUI(m);
		mainGUI.setVisible(true);
		
		Point p1 = new Point(2000, 1);

		m.getDataset().addPoint(p1);

		m.switchGraph(new ColumnChart());
		
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		mainGUI.rdbtnColumnChart.setSelected(false);
		
		SwitchGraphController controller = new SwitchGraphController(m, mainGUI);
		assertTrue(controller.act());
		
		assertTrue(mainGUI.btnShowhideAxisValues.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());

		m.switchGraph(new CartesianPlot());

		((CartesianPlot)m.getGraph()).setTrendLineVisible(true);
		((CartesianPlot)m.getGraph()).setFormulaVisible(true);
		
		mainGUI.rdbtnCartesianPlot.setSelected(false);
		mainGUI.rdbtnColumnChart.setSelected(true);
		assertTrue(controller.act());
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
	}
	
	

}

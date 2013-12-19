package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.DataSet;
import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestLoadFromFileController {

	Model m;
	MainGUI mainGUI;
	LoadFromFileController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		controller = new LoadFromFileController(m, mainGUI);
	}
	
	@Test
	public void testAddPoint() {
		LoadFromFileController controller = new LoadFromFileController(m, mainGUI);
		controller.addPoint("1,2");
		DataSet dataset = (DataSet)m.getDataSet();
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 0) == 1);
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 1) == 2);
	}
	
	@Test
	public void testClearPoints(){
		Point p = new Point(1, 2);
		DataSet dataset = (DataSet)m.getDataSet();
		dataset.addPoint(p);

		controller.clearPoints();
		
		dataset = (DataSet)m.getDataSet();
		assertTrue(dataset.size() == 0);
	}
	
	@Test
	public void testClearFormat(){
		Point p = new Point(1, 2);
		DataSet dataset = (DataSet)m.getDataSet();
		dataset.addPoint(p);
		
		controller.clearFormat();
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
	
		m = new Model();
		mainGUI = new MainGUI(m);
		dataset = (DataSet)m.getDataSet();
		
		Point p1 = new Point(1, 2);
		Point p2 = new Point(1 ,2);
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		controller = new LoadFromFileController(m, mainGUI);
		controller.clearFormat();
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
		assertTrue(mainGUI.btnShowHideTrendLine.isEnabled());
	
	}
	
	@Test
	public void testValidatePoint() {
		assertTrue(controller.validatePoint("1", "2"));
		assertFalse(controller.validatePoint("a", "2"));
	}

}

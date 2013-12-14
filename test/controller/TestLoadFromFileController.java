package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
		
		// judge if the point is already added into the dataset
		int size = m.getDataset().getPoints().size();
		assertTrue(m.getDataset().getPoints().get(size - 1).getX() == 1); 
		assertTrue(m.getDataset().getPoints().get(size - 1).getY() == 2); 
	}
	
	@Test
	public void testClearPoints(){
		Point p = new Point(1, 2);
		m.getDataset().addPoint(p);

		controller.clearPoints();
		assertTrue(m.getDataset().getPoints().size() == 0);
	}
	
	@Test
	public void testClearFormat(){
		Point p = new Point(1, 2);
		m.getDataset().addPoint(p);
		
		controller.clearFormat();
		assertFalse(mainGUI.btnShowHideFormula.isEnabled());
		assertFalse(mainGUI.btnShowHideTrendLine.isEnabled());
	
		m = new Model();
		mainGUI = new MainGUI(m);
		Point p1 = new Point(1, 2);
		Point p2 = new Point(1 ,2);
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
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

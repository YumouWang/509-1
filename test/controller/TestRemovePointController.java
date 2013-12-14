package controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.swing.DefaultListModel;

import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.MainGUI;

public class TestRemovePointController {

	Model m;
	MainGUI mainGUI;
	RemovePointController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.controller = new RemovePointController(m, mainGUI);
	}
	
	@Test
	public void testRemovePointSuccess() {

		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 3);
		
		m.getDataset().addPoint(p1);
		m.getDataset().addPoint(p2);
		
		listModel.add(0, p1.toString());
		listModel.add(0, p2.toString());
		
		int size = m.getDataset().getPoints().size();
		assertTrue(m.getDataset().getPoints().get(0) == p1);
		
		mainGUI.list.setSelectedIndex(0);
		assertTrue(controller.act());
		assertTrue(m.getDataset().getPoints().get(0) == p2);
		
		mainGUI.list.setSelectedIndex(0);
		controller.act();
		
		size = m.getDataset().getPoints().size();
		assertTrue(size == 0);
	}

	@Test
	public void testRemovePointFailure() {

		Point p1 = new Point(1, 2);
		m.getDataset().addPoint(p1);
		
		int size = m.getDataset().getPoints().size();
		assertFalse(controller.act());
		
		assertTrue(size == m.getDataset().getPoints().size());
	}
	
}

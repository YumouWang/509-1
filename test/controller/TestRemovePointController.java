package controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.swing.DefaultListModel;

import model.DataSet;
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
		
		DataSet dataset = (DataSet)m.dataSet;
		
		dataset.addPoint(p1);
		dataset.addPoint(p2);
		
		listModel.add(0, p1.toString());
		listModel.add(0, p2.toString());
		
		assertTrue(dataset.getCoordinate(0, 0) == p1.getX());
		assertTrue(dataset.getCoordinate(0, 1) == p1.getY());
		
		mainGUI.list.setSelectedIndex(0);
		assertTrue(controller.act());
		assertTrue(dataset.getCoordinate(0, 0) == p2.getX());
		assertTrue(dataset.getCoordinate(0, 1) == p2.getY());
		
		mainGUI.list.setSelectedIndex(0);
		controller.act();
		
		assertTrue(dataset.size() == 0);
	}

	@Test
	public void testRemovePointFailure() {
		DataSet dataset = (DataSet)m.dataSet;
		Point p1 = new Point(1, 2);
		dataset.addPoint(p1);
		
		int size = dataset.size();
		assertFalse(controller.act());
		
		assertTrue(size == dataset.size());
	}
	
}

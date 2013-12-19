package controller;

import static org.junit.Assert.*;

import javax.swing.DefaultListModel;

import model.DataSet;
import model.Model;
import model.Point;

import org.junit.Before;
import org.junit.Test;

import view.AddEditPointGUI;
import view.MainGUI;

public class TestEditOkController {

	Model m;
	MainGUI mainGUI;
	AddEditPointGUI addEditPointGUI;
	EditOkController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.addEditPointGUI = new AddEditPointGUI(m, mainGUI, false);
		this.controller = new EditOkController(m, addEditPointGUI);
	}
	
	@Test
	public void testActSuccess() {

		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		Point p = new Point(1, 2);
		
		DataSet dataset = (DataSet)m.dataSet;
		dataset.addPoint(p);
		listModel.add(0, p.toString());
		
		mainGUI.list.setSelectedIndex(0);
		
		addEditPointGUI.getTextField_x().setText("5");
		addEditPointGUI.getTextField_y().setText("6");
		assertTrue(controller.act());
		
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 0) == 5);
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 1) == 6);
	}
	
	@Test
	public void testActFailure() {
		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		
		DataSet dataset = (DataSet)m.dataSet;
		Point p = new Point(1, 2);
		
		dataset.addPoint(p);
		listModel.add(0, p.toString());
		
		EditOkController controller = new EditOkController(m, addEditPointGUI);
		mainGUI.list.setSelectedIndex(0);
		
		addEditPointGUI.getTextField_x().setText("aa");
		addEditPointGUI.getTextField_y().setText("5");
		assertFalse(controller.act());
		
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 0) == 1);
		assertTrue(dataset.getCoordinate(dataset.size() - 1, 1) == 2);	
	}
	
	@Test
	public void testValidatePoint() {
		addEditPointGUI.getTextField_x().setText("12");
		addEditPointGUI.getTextField_x().setText("15");
		
		EditOkController controller = new EditOkController(m, addEditPointGUI);
		assertTrue(controller.validatePoint("1", "2"));
		assertFalse(controller.validatePoint("a", "2"));
	}

}

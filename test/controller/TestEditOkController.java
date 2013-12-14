package controller;

import static org.junit.Assert.*;

import javax.swing.DefaultListModel;

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
		m.getDataset().addPoint(p);
		listModel.add(0, p.toString());
		
		int size = m.getDataset().getPoints().size();
		assertTrue(m.getDataset().getPoints().get((size - 1)) == p);
		
		mainGUI.list.setSelectedIndex(0);
		
		addEditPointGUI.getTextField_x().setText("5");
		addEditPointGUI.getTextField_y().setText("6");
		assertTrue(controller.act());
		
		assertTrue(m.getDataset().getPoints().get(size - 1).getX() == 5);
		assertTrue(m.getDataset().getPoints().get(size - 1).getY() == 6);
	}
	
	@Test
	public void testActFailure() {
		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		Point p = new Point(1, 2);
		m.getDataset().addPoint(p);
		listModel.add(0, p.toString());
		
		int size = m.getDataset().getPoints().size();
		assertTrue(m.getDataset().getPoints().get((size - 1)) == p);
		
		EditOkController controller = new EditOkController(m, addEditPointGUI);
		mainGUI.list.setSelectedIndex(0);
		
		addEditPointGUI.getTextField_x().setText("aa");
		addEditPointGUI.getTextField_y().setText("5");
		assertFalse(controller.act());
		
		assertTrue(m.getDataset().getPoints().get(size - 1).getX() == 1);
		assertTrue(m.getDataset().getPoints().get(size - 1).getY() == 2);
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

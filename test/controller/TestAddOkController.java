package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Model;

import org.junit.Before;
import org.junit.Test;

import view.AddEditPointGUI;
import view.MainGUI;

public class TestAddOkController {

	Model m;
	MainGUI mainGUI;
	AddEditPointGUI addEditPointGUI;
	AddOkController controller;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Model();
		this.mainGUI = new MainGUI(m);
		this.addEditPointGUI = new AddEditPointGUI(m, mainGUI, true);
		this.controller = new AddOkController(m, addEditPointGUI);
	}

	
	@Test
	public void testAddSuccess() {
		addEditPointGUI.getTextField_x().setText("1");
		addEditPointGUI.getTextField_y().setText("2");
		
		controller.mainGUI.setVisible(true);
		controller.mainGUI.graphPanel.setVisible(true);
		assertTrue(controller.act());
		
		addEditPointGUI = new AddEditPointGUI(m, mainGUI, true);
		addEditPointGUI.getTextField_x().setText("1");
		addEditPointGUI.getTextField_y().setText("2");
		
		controller = new AddOkController(m, addEditPointGUI);
		controller.mainGUI.graphPanel.setVisible(true);
		assertTrue(controller.act());
		
		// judge if the point is already added into the dataset
		int size = m.getDataset().getPoints().size();
		assertTrue(m.getDataset().getPoints().get(size - 1).getX() == 1); 
		assertTrue(m.getDataset().getPoints().get(size - 1).getY() == 2); 
	}
	
	@Test
	public void testAddFailure() {
		addEditPointGUI.getTextField_x().setText("1");
		addEditPointGUI.getTextField_y().setText("2b");

		int size = m.getDataset().getPoints().size();
		assertFalse(controller.act());
		
		// judge if the point is already added into the dataset
		assertTrue(m.getDataset().getPoints().size() == size); 
	}
	
	
	@Test
	public void testValidatePoint() {
		addEditPointGUI.getTextField_x().setText("12");
		addEditPointGUI.getTextField_x().setText("15");
		
		assertTrue(controller.validatePoint("1", "2"));
		assertFalse(controller.validatePoint("a", "2"));
	}
	

}

package controller;

import static org.junit.Assert.assertTrue;
import model.Model;

import org.junit.Test;

public class TestSaveToFileController {

	@Test
	public void testValidateSavePoints() {
		Model m = new Model();
		SaveToFileController controller = new SaveToFileController(m);
		assertTrue(m == controller.model);
	}

}

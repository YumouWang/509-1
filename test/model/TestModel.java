package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestModel {

	@Test
	public void testSwitchGraph() {
		Model m = new Model();
		ColumnChart g = new ColumnChart(); 
		m.switchGraph(g);
		assertTrue(m.getGraph() == g);
	}

}

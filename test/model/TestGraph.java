package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import view.Graph;

public class TestGraph {

	Graph g;
	
	@Before
	public void setUp() throws Exception {
		g = new Graph();
	}
	
	@Test
	public void testBackgroundVisibility() {
		g.setBackgroundVisible(true);
		assertTrue(g.isBackgroundVisible() == true);
		g.setBackgroundVisible(false);
		assertTrue(g.isBackgroundVisible() == false);
	}

	@Test
	public void testAxisVisibility() {
		g.setAxisValueVisible(true);
		assertTrue(g.isAxisValueVisible() == true);
		g.setAxisValueVisible(false);
		assertTrue(g.isAxisValueVisible() == false);
	}
}

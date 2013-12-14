package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCartesianPlot {

	CartesianPlot g;
	
	@Before
	public void setUp() throws Exception {
		g = new CartesianPlot();
	}
	
	@Test
	public void testCalculateFormula() {
		Dataset dataset = new Dataset();
		dataset.addPoint(new Point(1, 1));
		dataset.addPoint(new Point(2, 2));
		
		g.calculateFormula(dataset);
		assertTrue("y = 1.0 * x".equals(g.getFormula()));
		
		dataset = new Dataset();
		dataset.addPoint(new Point(0, 1));
		dataset.addPoint(new Point(1, 2));
		g.calculateFormula(dataset);
		assertTrue("y = 1.0 * x + 1.0".equals(g.getFormula()));
	}

	@Test
	public void testFormulaVisibility() {
		g.setFormulaVisible(false);
		assertTrue(g.isFormulaVisible() == false);
		
		g.setFormulaVisible(true);
		assertTrue(g.isFormulaVisible() == true);
	}
	
	@Test
	public void testTrendLineVisibility() {
		g.setTrendLineVisible(false);
		assertTrue(g.isTrendLineVisible() == false);
		
		g.setTrendLineVisible(true);
		assertTrue(g.isTrendLineVisible() == true);
	}
	
	
}

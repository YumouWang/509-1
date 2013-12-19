package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestDataset {
	DataSet dataset;
	
	@Before
	public void setUp() throws Exception {
		this.dataset = new DataSet();
	}
	
	@Test
	public void testAddPoint() {
		Point p1 = new Point(1,2);
		Point p2 = new Point(5,6);

		assertTrue(dataset.addPoint(p1));
		assertTrue(dataset.addPoint(p2));
		assertTrue(dataset.getNth(dataset.points.size() - 1) == p2);
		assertTrue(dataset.getMaxX() == p2.getX());
		assertTrue(dataset.getMaxY() == p2.getY());
		assertTrue(dataset.getMinX() == p1.getX());
		assertTrue(dataset.getMinY() == p1.getY());
	}
	
	@Test
	public void testUpdatePoint(){
		Point p = new Point(1,2);
		dataset.addPoint(p);
		
		Point newPoint = new Point(3, 4);
		assertTrue(dataset.updatePoint(0, newPoint));
		
		Point newPoint2 = new Point(5, 6);
		assertFalse(dataset.updatePoint(-1, newPoint2));
	}
	
	@Test
	public void testRemovePoint(){
		Point p = new Point(1,2);
		dataset.addPoint(p);
		assertTrue(dataset.removePoint(dataset.size() - 1));
		assertTrue(dataset.size() == 0);
		assertFalse(dataset.removePoint(-1));
	}


}

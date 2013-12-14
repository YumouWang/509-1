package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPoint {
	Point p;
	
	@Before
	public void setUp() throws Exception {
		p = new Point(3, 5);
	}
	
	@Test
	public void testPointValue() {
		assertTrue(p.getX() == 3);
		assertTrue(p.getY() == 5);
		
		p.setX(10);
		p.setY(20);
		assertTrue(p.getX() == 10);
		assertTrue(p.getY() == 20);
	}
	
	@Test
	public void testPointToString() {
		assertTrue(p.toString().equals(3.0 + "," + 5.0));
	}

}

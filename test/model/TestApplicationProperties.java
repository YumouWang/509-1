package model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestApplicationProperties {

	
	ApplicationProperties applicationProperties;
	
	@Before
	public void setUp() throws Exception {
		this.applicationProperties = ApplicationProperties.getInstance();
	}
	
	@Test
	public void testSingleton() {
		applicationProperties.properties.setProperty("test", "1");
		assertTrue(applicationProperties == ApplicationProperties.getInstance());
	}
	
	@Test
	public void testSetProperties() {
		applicationProperties.properties.setProperty("test", "1");
		assertTrue("1".equals(applicationProperties.properties.get("test")));
	}

}

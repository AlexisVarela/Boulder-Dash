package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class MapTest {

	private Map map;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.map = new Map();
    }

    @After
    public void tearDown() throws Exception {
    }

	@Test
	public void generatedMap() {
		Assert.assertTrue(this.map.getGeneratedMap().isEmpty());
	}
}

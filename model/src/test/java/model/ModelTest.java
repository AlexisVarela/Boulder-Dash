package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ModelTest {
    private Model model;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.model = new Model();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMap() {
    	Assert.assertThat(this.model.getMap(), instanceOf(Map.class));
	}
    
    @Test
	public void getPlayer() {
    	Assert.assertThat(this.model.getPlayer(), instanceOf(Player.class));
	}
    
    @Test
	public void setPlayer() {
    	Player p1 = new Player();
    	this.model.setPlayer(p1);
    	Assert.assertEquals(this.model.getPlayer(), p1);
	}

}

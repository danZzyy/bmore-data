/**
 * 
 */
package bmore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bmore.spring.model.CustomLocation;
import com.bmore.spring.service.CustomLocService;

/**
 * @author Dan
 * JUnit Test of CustomLocService
 * Tests the custom_location database table, as well as the CustomLocation model and  indirectly the CustomLocDao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CustomLocServiceTest {
	
	@Autowired
	private CustomLocService customLocService;
	
	private CustomLocation testLoc;

	@Before
	public void setup(){
		testLoc = new CustomLocation();
	}
	
	private void populateAll(){
		testLoc.setId(0); //value never used in DB
		testLoc.setName("Test Location");
		testLoc.setLat(0);
		testLoc.setLng(0);
		testLoc.setAddress("1234 Test St");
		testLoc.setAccessibility("YES");
	}
	
	@Test
	public void testSetup(){
		assertNotEquals(testLoc, null);
		
	}
	
	@Test
	public void testAlreadyExists(){
		populateAll();
		assertNull(customLocService.findById(testLoc.getId()));
	}
	
	@Test
	public void testCreateRemove(){
		populateAll();
		customLocService.saveLoc(testLoc);
		assertNotNull(customLocService.findById(testLoc.getId()));
		customLocService.deleteItem(testLoc);
		assertNull(customLocService.findById(testLoc.getId()));
	}
	
	@Test(expected = javax.persistence.PersistenceException.class)
	public void testRequiredFields(){
		testLoc.setId(0);
		//leave out accessibility, lat, lng which all have NOT NULL constraint
		customLocService.saveLoc(testLoc);
		assertNull(customLocService.findById(testLoc.getId()));
		
	}
}

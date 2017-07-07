package bmore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bmore.spring.model.BikeFacility;
import com.bmore.spring.model.Grocery;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.GroceryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class GroceryServiceTest {
	@Autowired
	GroceryService groceryService;
	
	@Test
	public void testGetAll(){
		assertNotNull(groceryService.getAll());
		assertFalse(groceryService.getAll().isEmpty());
		assertTrue(groceryService.getAll().get(0) instanceof Grocery);
	}
}

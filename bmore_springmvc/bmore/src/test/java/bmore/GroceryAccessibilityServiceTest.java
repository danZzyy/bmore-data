package bmore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.BikeFacility;
import com.bmore.spring.model.Grocery;
import com.bmore.spring.model.GroceryAccessibility;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.GroceryAccessibilityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class GroceryAccessibilityServiceTest {
	@Autowired
	GroceryAccessibilityService groceryAccessibilityService;
	
	@Test
	public void testGetAll(){
		ArrayList<GroceryAccessibility> grocAccList = (ArrayList<GroceryAccessibility>) groceryAccessibilityService.getAll();
		assertNotNull(grocAccList);
		assertFalse(grocAccList.isEmpty());
		assertTrue(grocAccList.get(0) instanceof GroceryAccessibility);
		assertTrue(grocAccList.get(0).getAccessibility() instanceof Accessibility);
		assertTrue(grocAccList.get(0).getGrocery() instanceof Grocery);
	}
}

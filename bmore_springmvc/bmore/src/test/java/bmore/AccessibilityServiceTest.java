package bmore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.BikeFacility;
import com.bmore.spring.service.AccessibilityService;
import com.bmore.spring.service.BikeFacilityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AccessibilityServiceTest {

	@Autowired
	AccessibilityService accessbilityService;
	
	@Test
	public void testGetAll(){
		assertNotNull(accessbilityService.getAll());
		assertFalse(accessbilityService.getAll().isEmpty());
		assertTrue(accessbilityService.getAll().get(0) instanceof Accessibility);
	}
}

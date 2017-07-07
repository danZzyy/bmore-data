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
import com.bmore.spring.model.Trail;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.TrailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class BikeFacilityServiceTest {

	@Autowired
	BikeFacilityService bikeFacilityService;
	
	@Test
	public void testGetAll(){
		assertNotNull(bikeFacilityService.getAll());
		assertFalse(bikeFacilityService.getAll().isEmpty());
		assertTrue(bikeFacilityService.getAll().get(0) instanceof BikeFacility);
	}
}

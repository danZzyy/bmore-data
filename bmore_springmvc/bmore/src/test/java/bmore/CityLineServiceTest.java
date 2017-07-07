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
import com.bmore.spring.model.CityLine;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.CityLineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CityLineServiceTest {

	@Autowired
	CityLineService cityLineService;
	
	@Test
	public void testGetAll(){
		assertNotNull(cityLineService.getAll());
		assertFalse(cityLineService.getAll().isEmpty());
		assertTrue(cityLineService.getAll().get(0) instanceof CityLine);
	}
}

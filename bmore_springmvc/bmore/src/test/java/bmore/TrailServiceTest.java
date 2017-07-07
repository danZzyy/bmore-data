package bmore;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bmore.spring.model.Trail;
import com.bmore.spring.service.TrailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TrailServiceTest {

	@Autowired
	TrailService trailService;
	
	@Test
	public void testGetAll(){
		assertNotNull(trailService.getAll());
		assertFalse(trailService.getAll().isEmpty());
		assertTrue(trailService.getAll().get(0) instanceof Trail);
	}
}

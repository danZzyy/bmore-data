package com.bmore.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.bmore.spring.model.BikeFacility;
import com.bmore.spring.model.FeatureCollection;
import com.bmore.spring.model.Grocery;
import com.bmore.spring.model.GroceryAccessibility;
import com.bmore.spring.model.geoJSONable;
import com.bmore.spring.service.AccessibilityService;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.CityLineService;
import com.bmore.spring.service.GroceryAccessibilityService;
import com.bmore.spring.service.GroceryService;
import com.bmore.spring.service.TrailService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	GroceryAccessibilityService grocAccService;
	
	@Autowired
	BikeFacilityService bikeFacilityService;
	
	@Autowired
	TrailService trailService;
	
	@Autowired
	CityLineService cityLineService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		//grocery geoJSON
		FeatureCollection groceryAccFeatureCollection = new FeatureCollection();
		
		ArrayList<geoJSONable> grocList =  (ArrayList) grocAccService.getAll();
		groceryAccFeatureCollection.setFeatures(grocList);
		
		model.addAttribute("groc", groceryAccFeatureCollection.toJSON());
		
		
		//city_line geoJSON
		FeatureCollection cityLineFeatureCollection = new FeatureCollection();
		
		ArrayList<geoJSONable> cityLineList =  (ArrayList) cityLineService.getAll();
		cityLineFeatureCollection.setFeatures(cityLineList);
		
		model.addAttribute("city_line", cityLineFeatureCollection.toJSON());
		
		//bike_fac geoJSON
		FeatureCollection bikeFacilityFeatureCollection = new FeatureCollection();
	
		ArrayList<geoJSONable> bikeFacList =  (ArrayList) bikeFacilityService.getAll();
		bikeFacilityFeatureCollection.setFeatures(bikeFacList);
			
		model.addAttribute("bike_fac", bikeFacilityFeatureCollection.toJSON());
		
		//trails geoJSON
		FeatureCollection trailsFeatureCollection = new FeatureCollection();
				
		ArrayList<geoJSONable> trailsList =  (ArrayList) trailService.getAll();
		trailsFeatureCollection.setFeatures(trailsList);
				
		model.addAttribute("trails", trailsFeatureCollection.toJSON());
		
		
		
		return "home";
	}
	
}

package com.bmore.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bmore.spring.model.BikeFacility;
import com.bmore.spring.model.CityLine;
import com.bmore.spring.model.CustomLocation;
import com.bmore.spring.model.FeatureCollection;
import com.bmore.spring.model.Grocery;
import com.bmore.spring.model.GroceryAccessibility;
import com.bmore.spring.model.Trail;
import com.bmore.spring.model.geoJSONable;
import com.bmore.spring.service.BikeFacilityService;
import com.bmore.spring.service.CityLineService;
import com.bmore.spring.service.CustomLocService;
import com.bmore.spring.service.GroceryAccessibilityService;
import com.bmore.spring.service.TrailService;

@RestController
public class LocationController {
	
	@Autowired
	CustomLocService customLocService;
	
	@Autowired
	GroceryAccessibilityService grocAccService;
	
	@Autowired
	BikeFacilityService bikeFacilityService;
	
	@Autowired
	TrailService trailService;
	
	@Autowired
	CityLineService cityLineService;
	
	// retrieve all Grocery
	@RequestMapping(value = "/groc/", method = RequestMethod.GET)
	public ResponseEntity<List<GroceryAccessibility>> listAllGroc(){
		ArrayList<GroceryAccessibility> grocs = (ArrayList<GroceryAccessibility>) grocAccService.getAll();
		
		if(grocs.isEmpty()){
			return new ResponseEntity<List<GroceryAccessibility>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<GroceryAccessibility>>(grocs, HttpStatus.OK);
	}
	
	// retrieve all BikeFacility
	@RequestMapping(value = "/bike_fac/", method = RequestMethod.GET)
	public ResponseEntity<List<BikeFacility>> listAllBike(){
		ArrayList<BikeFacility> bikeFacs = (ArrayList<BikeFacility>) bikeFacilityService.getAll();
		
		if(bikeFacs.isEmpty()){
			return new ResponseEntity<List<BikeFacility>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BikeFacility>>(bikeFacs, HttpStatus.OK);
	}
	
	// retrieve all Trail
	@RequestMapping(value = "/trail/", method = RequestMethod.GET)
	public ResponseEntity<List<Trail>> listAllTrail(){
		ArrayList<Trail> trails = (ArrayList<Trail>) trailService.getAll();
		
		if(trails.isEmpty()){
			return new ResponseEntity<List<Trail>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Trail>>(trails, HttpStatus.OK);
	}
	
	// retrieve all CityLine
	@RequestMapping(value = "/city_line/", method = RequestMethod.GET)
	public ResponseEntity<List<CityLine>> listAllCityLine(){
		ArrayList<CityLine> cityLine = (ArrayList<CityLine>) cityLineService.getAll();
		
		if(cityLine.isEmpty()){
			return new ResponseEntity<List<CityLine>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CityLine>>(cityLine, HttpStatus.OK);
	}
	
	// retrieve all CustomLocation
	@RequestMapping(value = "/location/", method = RequestMethod.GET )
	public ResponseEntity<List<CustomLocation>> listAllLoc(){
		ArrayList<CustomLocation> locs = (ArrayList<CustomLocation>) customLocService.findAll();
		
		if(locs.isEmpty()){
			return new ResponseEntity<List<CustomLocation>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CustomLocation>>(locs, HttpStatus.OK);
	}
	
	// Retrieve Single Item
	@RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomLocation> getLoc(@PathVariable("id") int id){
		// add logging
		CustomLocation loc = customLocService.findById(id);
		if(loc == null) {
			// add logging
			return new ResponseEntity<CustomLocation>(loc, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomLocation>(loc, HttpStatus.OK);
	}
	
	// Create Item
	@RequestMapping(value = "/location/", method = RequestMethod.POST)
	public ResponseEntity<Void> createLoc(@RequestBody CustomLocation loc, UriComponentsBuilder ucBuilder){
		// add logging
		if(customLocService.exists(loc)){
			// add logging
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		customLocService.saveLoc(loc);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(loc.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	// Update Item
	@RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
	public ResponseEntity<List<CustomLocation>> updateItem(@PathVariable("id") int id){
		return null;
	}
	
	// Delete Item
	@RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<CustomLocation>> deleteItem(@PathVariable("id") int id){
		return null;
	}
	
	// Delete All
	@RequestMapping(value = "/location/", method = RequestMethod.DELETE)
	public ResponseEntity<List<CustomLocation>> deleteAll(){
		return null;
	}
}

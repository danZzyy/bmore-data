package bmore_spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImportData {
	
	public static void main(String args[]) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		JSONParser parser  = new JSONParser();
		try{
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bmore?" +"user=admin&password=admin");
			stmt = conn.createStatement();
			
			HashMap<String, Integer> accessibilityLookup = new HashMap<String, Integer>(); //accessibility rating foreign key
			rs = stmt.executeQuery("SELECT * FROM accessibility_lookup;");
			while(rs.next()){
				Integer accId = rs.getInt("id");
				String rating = rs.getString("accessibility");
				accessibilityLookup.put(rating, accId);
				System.out.println(accId + " " + rating);
			}
			
			HashMap<String, Integer> supermarketLookup = new HashMap<String, Integer>(); //accessibility rating foreign key
			rs = stmt.executeQuery("SELECT * FROM supermarket_lookup;");
			while(rs.next()){
				Integer typeId = rs.getInt("id");
				String type = rs.getString("supermarket_type");
				supermarketLookup.put(type, typeId);
				System.out.println(typeId + " " + type);
			}
			JSONObject grocJsonObj = (JSONObject) parser.parse(new FileReader("data/groc_acc.json"));
			JSONArray grocFeatures = (JSONArray) grocJsonObj.get("features");
			Iterator grocFeatureIterator = grocFeatures.iterator();
			int groc_id = 0;
			
			while (grocFeatureIterator.hasNext()){
				JSONObject feature = (JSONObject) grocFeatureIterator.next();
				JSONObject geom = (JSONObject) feature.get("geometry");
				JSONArray coors = (JSONArray) geom.get("coordinates");
				double lng = (double) coors.get(0);
				double lat = (double) coors.get(1);
				JSONObject prop = (JSONObject) feature.get("properties");
				String name = (String) prop.get("name");
				String type = (String) prop.get("type");
				int typeIndex = supermarketLookup.get(type);
				String address = (String) prop.get("address");
				String accessibility = (String) prop.get("accessibility");
				int accIndex = accessibilityLookup.get(accessibility);
				String coorPoint = String.format("POINT(%.7f,%.7f)", lng, lat);
				//System.out.println(coorPoint);
				
				//insert into grocery table
				String insertGroc = "INSERT INTO grocery (id, name, address, type, coors) VALUES (%d, \"%s\", \"%s\", %d, %s);";
				PreparedStatement grocSt = conn.prepareStatement(String.format(insertGroc, groc_id, name, address, typeIndex, coorPoint));
				//grocSt.executeUpdate();
				//conn.commit();
				//insert into grocery_acc table
				String insertGrocAcc = "INSERT INTO grocery_accessibility (groc_id, accessibility) VALUES (%d, %d);";
				PreparedStatement grocAccSt = conn.prepareStatement(String.format(insertGrocAcc, groc_id, accIndex));
				//grocAccSt.executeUpdate();
				
				
				groc_id ++;
			}
			
			//bike_fac MultiLineString
			JSONObject citylineJsonObj = (JSONObject) parser.parse(new FileReader("data/city-line.geojson"));
			JSONArray cityFeatures = (JSONArray) citylineJsonObj.get("features");
			Iterator cityFeatureIterator = cityFeatures.iterator();
			while(cityFeatureIterator.hasNext()){
				String geomString = "MULTILINESTRING(";
				JSONObject feature = (JSONObject) cityFeatureIterator.next();
				JSONObject geom = (JSONObject) feature.get("geometry");
				JSONArray coors = (JSONArray) geom.get("coordinates");
				//construct MultiLineString
				for(int i = 0; i < coors.size(); i ++){
					//construct each line in multiLineString
					geomString += "LINESTRING(";
					JSONArray line = (JSONArray) coors.get(i);
					for(int p = 0; p < line.size(); p++){
						//points in each line
						JSONArray point = (JSONArray) line.get(p);
						geomString += "POINT(" + point.get(0) + "," + point.get(1) + ")";
						
						if(p < line.size()-1) geomString += ",";
					}
					geomString += ")";
					
					if(i < coors.size()-1) geomString += ",";
				}
				geomString += ")";
				String insertCity = "INSERT INTO city_line (geom) VALUES (%s);";
				PreparedStatement citySt = conn.prepareStatement(String.format(insertCity, geomString));
				citySt.executeUpdate();
			}
			
			//trails MultiLineString data
			JSONObject trailJsonObj = (JSONObject) parser.parse(new FileReader("data/trails.json"));
			JSONArray trailFeatures = (JSONArray) trailJsonObj.get("features");
			Iterator trailFeatureIterator = trailFeatures.iterator();
			while(trailFeatureIterator.hasNext()){
				//MULTILINESTRING(LINESTRING(POINT(1,1),POINT(2,2),POINT(3,3)),LINESTRING(POINT(4,4),POINT(5,5)))
				String geomString = "MULTILINESTRING(";
				JSONObject feature = (JSONObject) trailFeatureIterator.next();
				JSONObject geom = (JSONObject) feature.get("geometry");
				JSONArray coors = (JSONArray) geom.get("coordinates");
				//construct MultiLineString
				for(int i = 0; i < coors.size(); i ++){
					//construct each line in multiLineString
					geomString += "LINESTRING(";
					JSONArray line = (JSONArray) coors.get(i);
					for(int p = 0; p < line.size(); p++){
						//points in each line
						JSONArray point = (JSONArray) line.get(p);
						geomString += "POINT(" + point.get(0) + "," + point.get(1) + ")";
						
						if(p < line.size()-1) geomString += ",";
					}
					geomString += ")";
					
					if(i < coors.size()-1) geomString += ",";
				}
				geomString += ")";
				
				String insertTrail = "INSERT INTO trail (geom) VALUES (%s);";
				PreparedStatement trailSt = conn.prepareStatement(String.format(insertTrail, geomString));
				//trailSt.executeUpdate();
			}
			
			//bike_fac MultiLineString
			JSONObject bikeJsonObj = (JSONObject) parser.parse(new FileReader("data/bike-facilities.json"));
			JSONArray bikeFeatures = (JSONArray) bikeJsonObj.get("features");
			Iterator bikeFeatureIterator = bikeFeatures.iterator();
			while(bikeFeatureIterator.hasNext()){
				String geomString = "MULTILINESTRING(";
				JSONObject feature = (JSONObject) bikeFeatureIterator.next();
				JSONObject geom = (JSONObject) feature.get("geometry");
				JSONArray coors = (JSONArray) geom.get("coordinates");
				//construct MultiLineString
				for(int i = 0; i < coors.size(); i ++){
					//construct each line in multiLineString
					geomString += "LINESTRING(";
					JSONArray line = (JSONArray) coors.get(i);
					for(int p = 0; p < line.size(); p++){
						//points in each line
						JSONArray point = (JSONArray) line.get(p);
						geomString += "POINT(" + point.get(0) + "," + point.get(1) + ")";
						
						if(p < line.size()-1) geomString += ",";
					}
					geomString += ")";
					
					if(i < coors.size()-1) geomString += ",";
				}
				geomString += ")";
				String insertBike = "INSERT INTO bike_fac (geom) VALUES (%s);";
				PreparedStatement bikeSt = conn.prepareStatement(String.format(insertBike, geomString));
				//bikeSt.executeUpdate();
			}
			
			rs = stmt.executeQuery("SELECT * FROM grocery");
			while(rs.next()){
				System.out.println(rs.getString("id"));
			}
		}catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}

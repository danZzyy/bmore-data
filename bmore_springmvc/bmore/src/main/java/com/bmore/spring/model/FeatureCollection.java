package com.bmore.spring.model;

import java.util.ArrayList;

public class FeatureCollection implements geoJSONable {

	private ArrayList<geoJSONable> features;
	
	public FeatureCollection(){
		this.features = new ArrayList<geoJSONable>();
	}
	
	@Override
	public String toJSON() {
		String json = "{ \"type\": \"FeatureCollection\",\"features\": [";
		for(int j = 0; j < features.size(); j++){
			
			json += features.get(j).toJSON();
			
			if( j < (features.size() - 1)){ //no trailing comma
				json += ",";
			}
		}
		json += "]}";
		return json;
	}

	public ArrayList<geoJSONable> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<geoJSONable> features) {
		this.features = features;
	}
	
	//Add feature to FeatureCollection
	public void addFeature(geoJSONable feature){
		features.add(feature);
	}

}

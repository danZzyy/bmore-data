
function convertGrocs(objectArray){
	
	var featureCollection = {
		  "type": "FeatureCollection",
		  "features": []
	};
	
	objectArray.forEach(function(f){
		var feature = { "type": "Feature",
	        "geometry":{
	          "type": "Point",
	          "coordinates": [f.grocery.lng, f.grocery.lat]
	        },
	        "properties": {
	          "id": f.grocery.grocId,
	          "name": f.grocery.name,
	          "address": f.grocery.address,
	          "store_type": f.grocery.type.type,
	          "accessibility": f.accessibility.accessibility
	        }
	      };
		featureCollection.features.push(feature);
	});
	return featureCollection;
}

// for trails, bike_fac, and city_line
function convertMultiLineString(objectArray){
	
	var featureCollection = {
		  "type": "FeatureCollection",
		  "features": []
	};
	
	objectArray.forEach(function(f){
		var feature = { "type": "Feature",
		        "geometry":{
		          "type": "MultiLineString",
		          "coordinates": JSON.parse(f.geom)
		        },
		        "properties": {
		          "id": f.id
		        }
		      };
			featureCollection.features.push(feature);
		});
		return featureCollection;
}
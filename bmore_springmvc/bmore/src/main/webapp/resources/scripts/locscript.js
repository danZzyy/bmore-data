	  var geocoder;
      
      var map;
      
      var currentPt = null;
      
      var currentLoc = {id:null, name:'', address:'', lat:null, lng:null, accessibility:''};
      
      var bikepaths = [];
      
      // intialize map after html loads
      function init(){
    	  geocoder = new google.maps.Geocoder();
    	  map = L.map('map').setView([39.30, -76.619], 12);
    	  
    	  L.tileLayer('https://api.mapbox.com/styles/v1/dzadoroz/cirz64sz2003sg9kw8gpd8p2w/tiles/256/{z}/{x}/{y}?access_token={accessToken}', {
    		    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    		    maxZoom: 18,
    		    id: 'dzadoroz.0le4fa15',
    		    accessToken: 'pk.eyJ1IjoiZHphZG9yb3oiLCJhIjoiY2lxbGUwcDAxMDAxbWZwbmhkdXJhdW52NCJ9.0yepqDp3o0FdJ0t2CLcQlw'
    	  }).addTo(map);
    	  
      }
      
      // load Bmore data into map
      function populateMap(){
    	  //render city border
    	  city_line.features.forEach(function(feature){
    	    L.geoJSON(feature, { style: citylineStyle }).addTo(map);
    	  });

    	  //render trails
    	  trails.features.forEach(function(feature){
    	    L.geoJSON(feature, { style: trailStyle }).addTo(map);
    	    bikepaths.push(feature);
    	  });

    	  bike_fac.features.forEach(function(feature){
    	    L.geoJSON(feature, { style: bikeStyle}).addTo(map);
    	    bikepaths.push(feature);
    	  });
    	  
    	  
    	  groc.features.forEach(function(feature){
    		    // first map
    		    var popupText = "<b>" + feature.properties.name + "</b><br>" + feature.properties.store_type + "<br>Accessibility: " + feature.properties.accessibility + "<br>" + feature.properties.address ;

    		    var accMarker = L.geoJSON(feature, {
    		      pointToLayer: function (f, latlng) {
    		        if(feature.properties.accessibility == "YES") {
    		          return L.circleMarker(latlng, yesMarkerOptions);
    		        }
    		        else if(feature.properties.accessibility == "SOMEWHAT") {
    		          return L.circleMarker(latlng, somewhatMarkerOptions);
    		        }
    		        else {
    		          return L.circleMarker(latlng, noMarkerOptions);
    		        }
    		      }
    		    }).addTo(map);
    		    accMarker.bindPopup(popupText);
    	  });
      }
      
      // use Google's geocoder to get coordinates from valid street address
      function geocodeAddress(){
    	  var addr = $('#address').val();
    	  var $addressResults = $('#addressResults');
    	  
    	  //google's geocoder takes address and spits out coordinates if found
    	  geocoder.geocode({address: addr}, function(results, status){
    		  if(status == google.maps.GeocoderStatus.OK) {
    			  var lat = results[0].geometry.location.lat();
    			  var lng = results[0].geometry.location.lng();
    			  $addressResults.html("Determining accessibility of " + addr);
    			  addToMap(lat, lng, addr);
    		  }
    		  else {
    			  $addressResults.html("Could not find " + addr);
    		  }
    	  });
    	  
      }
      
      function toGeoJSON(lng, lat, addr){
    	  var feature = { "type": "Feature",
                  "geometry":{
                    "type": "Point",
                    "coordinates": [lng, lat]
                  },
                  "properties": {
                    "address": addr
                  }
                };
    	  return feature;
      }
      
      // input geoJSON point
      function getAcc(f){
    	  var quarterMile = turf.buffer(f, 0.25, "miles");
    	  var halfMile = turf.buffer(f, 0.5, "miles");
    	  f.properties.accessibility = "NO";
    	  for( var i = 0; i < bikepaths.length; i ++){
    	    if(turf.intersect(bikepaths[i], quarterMile)){
    	      f.properties.accessibility = "YES";
    	    }
    	    else if(turf.intersect(bikepaths[i], halfMile) && f.properties.accessibility != "YES"){
    	      f.properties.accessibility = "SOMEWHAT";
    	    }
    	  }
    	  
    	  return f;
      }
      
      // plot point from geocoding
      function addToMap(lat, lng, addr){
    	  var featureGeoJSON = getAcc(toGeoJSON(lng, lat, addr));
    	  currentLoc.lat = lat;
    	  currentLoc.lng = lng;
    	  currentLoc.address = addr;
    	  currentPt = L.geoJSON(featureGeoJSON, {
    	      pointToLayer: function (f, latlng) {
    	    	  
    	    	currentLoc.accessibility = f.properties.accessibility;
    	    	
	    	  	return L.marker(latlng);
    	      }
    	  }).addTo(map);
    	  
    	  map.setView([lat, lng], 14);
    	  //$('#addressResults').html(addr + " accessibility is " + featureGeoJSON.properties.accessibility);
    	  
      }
      
      // removes form point from map
      function removePoint(){
    	  if(currentPt != null){
    		  map.removeLayer(currentPt);
    		  currentPt = null;
    		  recenterMap();
    		  resetCurrentLoc();
    	  }
      }
      
      // render pie chart with current data
      function updateChart(){
    	  AmCharts.makeChart("piechart", {
    		  "type": "pie",
    		  "dataProvider": [{
    		    "accessibility": "Accessible",
    		    "count": accCount["YES"],
    		    "color": "green"
    		  },
    		  {
      		    "accessibility": "Somewhat Accessible",
      		    "count": accCount["SOMEWHAT"],
      		    "color": "yellow"
      		  },
      		{
      		    "accessibility": "Not Accessible",
      		    "count": accCount["NO"],
      		    "color": "red"
      		  }],
	      	  "valueField": "count",
	      	  "titleField": "accessibility",
	      	  "colorField": "color",
	      	  "labelColorField": "color",
	      	  "balloon": {
	      	    "fixedPosition": true
      	  		}
    	  });
      }
    	  
      function resetCurrentLoc(){
    	  currentLoc = {id:null, name:'', address:'', lat:null, lng:null, accessibility:''};  
      }
      
      function recenterMap(){
    	  map.setView([39.30, -76.619], 12);
      }
      $('#coorBtn').on('click', geocodeAddress);
      $('#removePtBtn').on('click', removePoint);
      
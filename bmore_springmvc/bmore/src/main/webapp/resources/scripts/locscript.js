var geocoder;
      
      var map;
      
      var currentPt = null;
      
      function init(){
    	  geocoder = new google.maps.Geocoder();
    	  map = L.map('map').setView([39.30, -76.619], 13);
    	  
    	  L.tileLayer('https://api.mapbox.com/styles/v1/dzadoroz/cirz64sz2003sg9kw8gpd8p2w/tiles/256/{z}/{x}/{y}?access_token={accessToken}', {
    		    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    		    maxZoom: 18,
    		    id: 'dzadoroz.0le4fa15',
    		    accessToken: 'pk.eyJ1IjoiZHphZG9yb3oiLCJhIjoiY2lxbGUwcDAxMDAxbWZwbmhkdXJhdW52NCJ9.0yepqDp3o0FdJ0t2CLcQlw'
    	  }).addTo(map);
      }
      
      function geocodeAddress(){
    	  
    	  var addr = $('#address').val();
    	  var $addressResults = $('#addressResults');
    	  //render standby message
    	  
    	  //google's geocoder takes address and spits out coordinates if found
    	  geocoder.geocode({address: addr}, function(results, status){
    		  if(status == google.maps.GeocoderStatus.OK) {
    			  var lat = results[0].geometry.location.lat();
    			  var lng = results[0].geometry.location.lng();
    			  $addressResults.html(addr + " found at " + lat + " , " + lng);
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
      
      function addToMap(lat, lng, addr){
    	  var yesMarkerOptions = {
    			   radius: 6,
    			   fillColor: "green",
    			   color: "black",
    			   weight: 1,
    			   opacity: 1,
    			   fillOpacity: 0.9
    			};
    	  var featureGeoJSON = toGeoJSON(lng, lat, addr);
    	  currentPt = L.geoJSON(featureGeoJSON, {
    	      pointToLayer: function (f, latlng) {
    	          return L.circleMarker(latlng, yesMarkerOptions);
    	      }
    	    }).addTo(map);
    	  map.setView([lat, lng], 14);
      }
      
      function removePoint(){
    	  if(currentPt != null){
    		  map.removeLayer(currentPt);
    		  currentPt = null;
    	  }
      }
      $('#coorBtn').on('click', geocodeAddress);
      $('#removePtBtn').on('click', removePoint);
      
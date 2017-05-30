function init(){
  var mymap = L.map('mapid').setView([39.28, -76.619], 11);

  L.tileLayer('https://api.mapbox.com/styles/v1/dzadoroz/cirz64sz2003sg9kw8gpd8p2w/tiles/256/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'dzadoroz.0le4fa15',
    accessToken: 'pk.eyJ1IjoiZHphZG9yb3oiLCJhIjoiY2lxbGUwcDAxMDAxbWZwbmhkdXJhdW52NCJ9.0yepqDp3o0FdJ0t2CLcQlw'
  }).addTo(mymap);

  //render city border
  city_line.features.forEach(function(feature){
    L.geoJSON(feature).addTo(mymap);
  });

  /* GROCERY
     8: name
     9: type
     14: [0]: street address

  */
  var trailStyle = {
      "color": "#ff7800",
      "weight": 3,
      "opacity": 0.65
  };
  //render trails
  trails.features.forEach(function(feature){
     L.geoJSON(feature, { style: trailStyle }).addTo(mymap);
  });

  var bikeStyle = {
      "color": "#ff0000",
      "weight": 2,
      "opacity": 0.65
  };

  bike_fac.features.forEach(function(feature){
    L.geoJSON(feature, { style: bikeStyle}).addTo(mymap);
  });

  var vendorMarkerOptions = {
     radius: 3,
     fillColor: "#44aa33",
     color: "#000",
     weight: 1,
     opacity: 1,
     fillOpacity: 0.8
};
  vendors.features.forEach(function(feature){
    L.geoJSON(feature, {
      pointToLayer: function (f, latlng) {
        return L.circleMarker(latlng, vendorMarkerOptions);
      }
    }).addTo(mymap);
  })
  var geocoder = new google.maps.Geocoder();
/*
  grocery.data.forEach( function(d){
    geocoder.geocode( { 'address': }, funcition(results, status){
      if (status == google.maps.GeocoderStatus.OK){

      }
    });
  }); */
}

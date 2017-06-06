var fs = require('fs');
var turf = require('turf');

var groc = require('./data/groc.geojson');
var bikelanes = require('./data/bike-facilities.geojson');
var trails = require('./data/trails.geojson');

var bikepaths = bikelanes.features.concat(trails.features);
//for each point in grocLayer:
//  turf.buffer() 1/4, 1/2 miles
//  turf.within() each circle and bikeLayer
//  -> assign rating: Accessible, Somewhat Accessible, Not Accessible
//color-code and render stores based on rating
//display stats:
// %of accessible stores
groc.features.forEach(function(f){
  var quarterMile = turf.buffer(f, 0.25, "miles");
  var halfMile = turf.buffer(f, 0.5, "miles");
  for( var i = 0; i < bikepaths.length; i ++){
    if(turf.intersect(bikepaths[i], quarterMile)){
      f.properties.accessibility = "YES";
    }
    else if(turf.intersect(bikepaths[i], halfMile) && f.properties.accessibility != "YES"){
      f.properties.accessibility = "SOMEWHAT";
    }
  }
});

fs.writeFile('./data/groc_acc.geojson', JSON.stringify(groc));

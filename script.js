var grocLayer; //grocery point layer
var bikeLayer = []; //trails/lanes as line layer

var tog = true;
// initializes Leaflet map
// function called when body loads, otherwise Leaflet loads before the map div and fails to render
function init(){
  grocLayer = groc_geoJSON.features;

  var mymap = L.map('mapid').setView([39.30, -76.619], 12);
  var map2 = L.map('map2').setView([39.30, -76.619], 12);

  L.tileLayer('https://api.mapbox.com/styles/v1/dzadoroz/cirz64sz2003sg9kw8gpd8p2w/tiles/256/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'dzadoroz.0le4fa15',
    accessToken: 'pk.eyJ1IjoiZHphZG9yb3oiLCJhIjoiY2lxbGUwcDAxMDAxbWZwbmhkdXJhdW52NCJ9.0yepqDp3o0FdJ0t2CLcQlw'
  }).addTo(mymap);

  L.tileLayer('https://api.mapbox.com/styles/v1/dzadoroz/cirz64sz2003sg9kw8gpd8p2w/tiles/256/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'dzadoroz.0le4fa15',
    accessToken: 'pk.eyJ1IjoiZHphZG9yb3oiLCJhIjoiY2lxbGUwcDAxMDAxbWZwbmhkdXJhdW52NCJ9.0yepqDp3o0FdJ0t2CLcQlw'
  }).addTo(map2);

  //render city border
  city_line.features.forEach(function(feature){
    L.geoJSON(feature, { style: citylineStyle }).addTo(mymap);
    L.geoJSON(feature, { style: citylineStyle }).addTo(map2);
  });

  //render trails
  trails.features.forEach(function(feature){
    var f = L.geoJSON(feature);
    bikeLayer.push(f);
    L.geoJSON(feature, { style: trailStyle }).addTo(mymap);
  });

  bike_fac.features.forEach(function(feature){
    var f = L.geoJSON(feature);
    bikeLayer.push(f);
    L.geoJSON(feature, { style: bikeStyle}).addTo(mymap);
  });

  L.layerGroup(bikeLayer, { style: bikepathStyle }).addTo(map2);

  var accessible = [];
  var somewhatAcccessible = [];
  var notAccessible = [];
  var yesCount = 0;
  var somewhatCount = 0;
  var noCount = 0;
  var grocTotal = grocLayer.length;

  grocLayer.forEach(function(feature){
    // first map
    var popupText = "<b>" + feature.properties.name + "</b><br>" + feature.properties.type + "<br>" + feature.properties.address;
    var grocMarker = L.geoJSON(feature, {
      pointToLayer: function (f, latlng) {
        if(feature.properties.type == "Full Supermarket") {
          return L.circleMarker(latlng, fullMarkerOptions);
        }
        else if(feature.properties.type == "Small Supermarket") {
          return L.circleMarker(latlng, smallMarkerOptions);
        }
        else {
          return L.circleMarker(latlng, ltdMarkerOptions);
        }
      }
    }).addTo(mymap);
    grocMarker.bindPopup(popupText);

    // second map

    var accMarker = L.geoJSON(feature, {
      pointToLayer: function (f, latlng) {
        if(feature.properties.accessibility == "YES") {
          yesCount ++;
          return L.circleMarker(latlng, yesMarkerOptions);
        }
        else if(feature.properties.accessibility == "SOMEWHAT") {
          somewhatCount++;
          return L.circleMarker(latlng, somewhatMarkerOptions);
        }
        else {
          noCount ++;
          return L.circleMarker(latlng, noMarkerOptions);
        }
      }
    }).addTo(map2);

  });
  $('#yesStat').html('<div class="bigStat">' + ((yesCount/grocTotal)*100).toFixed(1) + '%</div> of Grocery Stores are ACCESSIBLE');
  $('#somewhatStat').html('<div class="bigStat">' + ((somewhatCount/grocTotal)*100).toFixed(1) + '%</div> of Grocery Stores are SOMEWHAT ACCESSIBLE');;
  $('#noStat').html('<div class="bigStat">' + ((noCount/grocTotal)*100).toFixed(1) + '%</div> of Grocery Stores are NOT ACCESSIBLE');;

  $('#map2').css('height', '0px');
  $('#accLegend').css('height', '0px');
  $('#accLegend').css('visibility', 'hidden');

}

function toggleMap(){
  var $mapGeneral = $('#mapid');
  var $mapAccess = $('#map2');
  var $genLegend = $('#genLegend');
  var $accLegend = $('#accLegend');
  var $acc = $('#acc');
  var $gen = $('#gen');

  if(tog){ //change to accessibility map
    $mapGeneral.css('visibility', 'hidden');
    $mapGeneral.css('height', '0px');
    $genLegend.css('visibility', 'hidden');
    $genLegend.css('height', '0px');
    $gen.css('background-color', '#e8e8e8');
    $gen.css('color', 'black');

    $mapAccess.css('visibility', 'visible');
    $mapAccess.css('height', '500px');
    $accLegend.css('visibility', 'visible');
    $accLegend.css('height', '100px');
    $acc.css('background-color', '#0065ff');
    $acc.css('color', 'white');
  }
  else{ //change to general map
    $mapGeneral.css('visibility', 'visible');
    $mapGeneral.css('height', '500px');
    $genLegend.css('visibility', 'visible');
    $genLegend.css('height', '100px');
    $gen.css('background-color', '#0065ff');
    $gen.css('color', 'white');

    $mapAccess.css('visibility', 'hidden');
    $mapAccess.css('height', '0px');
    $accLegend.css('visibility', 'hidden');
    $accLegend.css('height', '0px');
    $acc.css('background-color', '#e8e8e8');
    $acc.css('color', 'black');
  }
  tog = !tog;

}

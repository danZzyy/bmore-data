<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css"
   integrity="sha512-07I2e+7D8p6he1SIM+1twR5TIrhUQn9+I6yjqD53JQjFiMf8EtC93ty0/5vJTZGF8aAocvHYNEDJajGdNx1IsQ=="
   crossorigin=""/>
   <script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"
  integrity="sha512-A7vV8IFfih/D732iSSKi20u/ooOfj/AGehOKq0f4vLT1Zr2Y+RX7C+w8A1gaSasGtRUZpF/NZgzSAu4/Gc41Lg=="
  crossorigin=""></script>
  <script src='https://npmcdn.com/@turf/turf@4.3.1/turf.min.js'></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
  <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
  <script src="<c:url value="/resources/scripts/mapStyles.js"/>"></script>
  <script src="<c:url value="/resources/scripts/script.js"/>"></script>
    <title>BMORE CITY DATA</title>
  </head>
  <script>
  	var groc_geoJSON = ${groc};
  	var city_line = ${city_line};
  	var trails = ${trails};
  	var bike_fac = ${bike_fac};
  </script>
  <body onload="init()">
      <header>
        <span><img class="bike" src="<c:url value="resources/img/bike.png"/>" alt=""></span>
        <span id="title">BALTIMORE BIKE DATA</span>
        <span><img class="bike" src="<c:url value="resources/img/bike.png"/>" alt=""></span>
        <br>
        Daniil Zadorozhnyy
      </header>
      <p><b>OVERVIEW</b></p>
      <p>In this project I will be looking at Baltimore City's geospatial data of bike paths/trails and grocery stores. I will determine what stores are accessible via bicycle and what the proportion is over the whole city.</p>
      <div id="map-container">
	      <div id="buttons">
	        <button id="toggle" type="button" onclick="toggleMap()">Toggle Map</button>
	        <span id="gen">General</span>
	        <span id="acc">Accessibility</span>
	        <button id="recenter" type="button" onclick="recenterMap()">Recenter Map</button>
	      </div>
	      <div id="mapid"></div>
	      <div id="map2"></div>
	      <div id="genLegend">
	       <table class="legendTable">
	         <tr>
	           <td>
	             <div>
	               <span><svg height="30" width="40">
	                 <line x1="10" y1="25" x2="35" y2="25" style="stroke:#ed1a1a;stroke-width:3" />
	               </svg></span>
	               <span>Bike Lane </span>
	             </div>
	             <div>
	               <span><svg height="30" width="40">
	                 <line x1="10" y1="25" x2="35" y2="25" style="stroke:#357254;stroke-width:3" />
	               </svg></span>
	               <span>Trail</span>
	             </div>
	           </td>
	           <td>
	             <div class="spacing"></div>
	           </td>
	           <td>
	             <div>
	               <span><svg height="41" width="20">
	                 <circle cx="10" cy="30" r="8" stroke="black" stroke-width="1" fill="#0065ff" />
	               </svg></span>
	               <span>Full Grocery Store</span>
	             </div>
	             <div>
	               <span><svg height="35" width="20">
	                 <circle cx="10" cy="27" r="6" stroke="black" stroke-width="1" fill="#66a0ff" />
	               </svg></span>
	               <span>Small Grocery Store</span>
	             </div>
	             <div>
	               <span><svg height="32" width="20">
	                 <circle cx="10" cy="25" r="4" stroke="black" stroke-width="1" fill="#d8e7ff" />
	               </svg></span>
	               <span>Limited Grocery Store</span>
	             </div>
	           </td>
	         </tr>
	       </table>
	     </div>
	     <div id="accLegend">
	       <table class="legendTable">
	         <tr>
	           <td>
	             <div>
	               <span><svg height="30" width="40">
	                 <line x1="10" y1="25" x2="35" y2="25" style="stroke:#0065ff;stroke-width:3" />
	               </svg></span>
	               <span>Bike Accessible Path </span>
	             </div>
	           </td>
	           <td>
	             <div class="spacing"></div>
	           </td>
	           <td>
	             <div>
	               <span><svg height="35" width="20">
	                 <circle cx="10" cy="27" r="6" stroke="black" stroke-width="1" fill="green" />
	               </svg></span>
	               <span>Accessible</span>
	             </div>
	             <div>
	               <span><svg height="35" width="20">
	                 <circle cx="10" cy="27" r="6" stroke="black" stroke-width="1" fill="yellow" />
	               </svg></span>
	               <span>Somewhat Accessible</span>
	             </div>
	             <div>
	               <span><svg height="35" width="20">
	                 <circle cx="10" cy="27" r="6" stroke="black" stroke-width="1" fill="red" />
	               </svg></span>
	               <span>Not Aceessible</span>
	             </div>
	           </td>
	         </tr>
	       </table>
	     </div>
     </div>
     <br>
     <p>The accessibility values of the stores found in the Accessibility Map were created with a rating system I came up with. </p>
     <ul>
       <li>Stores within 1/4 mile of a bike lane will be considered ACCESSIBLE</li>
       <li>Stores within 1/2 mile of a bike lane are SOMEWHAT ACCESSIBLE</li>
       <li>Finally, stores further than 1/2 mile from a bike lane are labeled NOT ACCESSIBLE</li>
     </ul>
     <p><b>STATISTICS</b></p>
     <div id="stats">
       <div id="yesStat"></div>
       <div id="somewhatStat"></div>
       <div id="noStat"></div>
     </div>
     <p><b>DATASETS</b></p>
     <ul>
       <li><a href="https://data.baltimorecity.gov/Health/Grocery-Stores/uuwk-975y">Grocery Stores</a></li>
       <li><a href="https://data.baltimorecity.gov/Transportation/On-Street-Bike-Facilities/2fxj-ph4e">Bike Facilities</a></li>
       <li><a href="https://data.baltimorecity.gov/Transportation/Multi-Use-Trails/ca8m-ir6r">Multi-USe Trails</a></li>
     </ul>
  </body>

</html>


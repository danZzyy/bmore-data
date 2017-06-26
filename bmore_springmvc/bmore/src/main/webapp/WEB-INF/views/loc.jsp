<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Custom Location App</title>  
    <style>
    .panel-heading, .tablecontainer, #mapformContainer{
    	margin-right: 15%;
    	margin-left: 15%;
    }
    #map{
    	width: 600px;
    	height: 500px;
    	margin-right: 10px;
    	border-radius: 10px;
    }
    label{
    	margin-right: 20px;
    }
    .formcontainer{
    	background-color: #eee;
    	border-radius: 10px;
    	padding: 20px;
    }
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
      
      #address{
      	width: 200px;
      }
 
 	header{
	  text-align: center;
	  display: block;
	  margin-left: auto;
	  margin-right: auto;
	  margin-top: 10px;
	  margin-bottom: 20px;
	}
	.bike {
	  height: 40px;
	}
	#title {
	  font-size: 3em;
	}
	span {
	  display: inline;
	}
	#coorBtn{
		color: red;
		font-size: 12pt;
	}
	#formTitle{
		text-align: center;
		font-size: 2em;
	}
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  </head>
  <body ng-app="locApp" class="ng-cloak" onload="init()">
  	<header>
        <span><img class="bike" src="<c:url value="../resources/img/bike.png"/>" alt=""></span>
        <span id="title">BALTIMORE BIKE DATA</span>
        <span><img class="bike" src="<c:url value="../resources/img/bike.png"/>" alt=""></span>
        <br>
        Daniil Zadorozhnyy
    </header>
      <div class="generic-container" ng-controller="LocController as ctrl">
          <div class="panel panel-default">
              <table id="mapformContainer">
              	<tr>
              		<td>
              			<div id="map"></div>
              		</td>
              		<td class="formcontainer" valign="top">
              			<div>
              				<div id="formTitle">Bike Accessibility</div>
              				<p>Find out the bike accessibility of your favorite places in Baltimore! Then save them and revisit them!</p>
              				<br>
             				<form ng-submit="ctrl.submit()" id="locFrom" name="locForm" class="form-horizontal">
		                      <input type="hidden" ng-model="ctrl.user.id" />
		                      <div class="row">
		                              <label class="col-md-2 control-lable" for="lname">Name</label>
		                              <div class="col-md-7">
		                                  <input type="text" ng-model="ctrl.loc.name" id="lname" class="username form-control input-sm" placeholder="What is this place called?" required ng-minlength="3"/>
		                              </div>
		                      </div>
		                      <br>   
		                       
		                      <div class="row">
		                              <label class="col-md-2 control-lable" for="address">Address</label>
		                              <div class="col-md-7">
		                                 <div class="input-group">
										  <input type="text" ng-model="ctrl.loc.address" id="address" class="form-control input-sm" placeholder="What is its address?"/>
										  <span class="input-group-btn">
										    <button class="btn btn-default" id="coorBtn" type="button">
										    	<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
											</button>
											<button class="btn btn-danger" type="button" id="removePtBtn">X</button>
										  </span>
										</div>
		                              </div>
		                      </div>
		                      <br>
		                      <div class="row">
		                      	<div id="addressResults"></div>
		                      </div>
		 					  <br>
		                      <div class="row">
		                          <div class="form-actions floatRight">
		                              <input type="submit"  value="{{!ctrl.loc.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="locForm.$invalid">
		                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="locForm.$pristine">Reset Form</button>
		                          </div>
		                      </div>
		                      <br>
		                  </form>
              			</div>
              		</td>
              	</tr>
              </table>
              
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of CustomLocations </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Address</th>
                              <th>Lat</th>
                              <th>Lng</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="l in ctrl.locs">
                              <td><span ng-bind="l.id"></span></td>
                              <td><span ng-bind="l.name"></span></td>
                              <td><span ng-bind="l.address"></span></td>
                              <td><span ng-bind="l.lat"></span></td>
                              <td><span ng-bind="l.lng"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(l.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(l.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="http://maps.google.com/maps/api/js?key=AIzaSyBqM1JuISJ7mUM_3cGk5VwgXGX-sW6JCno"></script>
      <link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css"
   integrity="sha512-07I2e+7D8p6he1SIM+1twR5TIrhUQn9+I6yjqD53JQjFiMf8EtC93ty0/5vJTZGF8aAocvHYNEDJajGdNx1IsQ=="
   crossorigin=""/>
   	  <script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"
  integrity="sha512-A7vV8IFfih/D732iSSKi20u/ooOfj/AGehOKq0f4vLT1Zr2Y+RX7C+w8A1gaSasGtRUZpF/NZgzSAu4/Gc41Lg=="
  crossorigin=""></script>
      <script src="<c:url value='/resources/scripts/app.js' />"></script>
      <script src="<c:url value='/resources/scripts/service/loc_service.js' />"></script>
      <script src="<c:url value='/resources/scripts/controller/loc_controller.js' />"></script>
      <script src="<c:url value='/resources/scripts/locscript.js'/>"></script>
      <script src="<c:url value='/resources/scripts/mapStyles.js'/>"></script>
  </body>
</html>
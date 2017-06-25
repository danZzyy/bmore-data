<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
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
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  </head>
  <body ng-app="locApp" class="ng-cloak">
  	<p>${custLoc}</p>
      <div class="generic-container" ng-controller="LocController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
             
              </div>
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
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/resources/scripts/app.js' />"></script>
      <script src="<c:url value='/resources/scripts/service/loc_service.js' />"></script>
      <script src="<c:url value='/resources/scripts/controller/loc_controller.js' />"></script>
  </body>
</html>
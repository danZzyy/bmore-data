'use strict';

angular.module('locApp').factory('LocService', ['$http', '$q', function($http, $q){
	var REST_SERVICE_URI = 'http://localhost:8080/app/location/';
	var BIKE_FAC_URI = 'http://localhost:8080/app/bike_fac/';
	var GROC_URI = 'http://localhost:8080/app/groc/';
	var TRAIL_URI = 'http://localhost:8080/app/trail/';
	var CITY_URI = 'http://localhost:8080/app/city_line/';
	
	var factory = {
		fetchBikeFacs: fetchBikeFacs,
		fetchGrocs: fetchGrocs,
		fetchTrails: fetchTrails,
		fetchCityLine: fetchCityLine,
		fetchAllLocs: fetchAllLocs,
		createLoc: createLoc,
		updateLoc: updateLoc,
		deleteLoc: deleteLoc
	}
	
	return factory;
	
	function fetchCityLine(){
		var deferred = $q.defer();
		$http.get(CITY_URI).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error fetching CityLine');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function fetchTrails(){
		var deferred = $q.defer();
		$http.get(TRAIL_URI).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error fetching all Trails');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function fetchGrocs(){
		var deferred = $q.defer();
		$http.get(GROC_URI).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error fetching all GroceryAccessibility');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function fetchBikeFacs(){
		var deferred = $q.defer();
		$http.get(BIKE_FAC_URI).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error fetching all BikeFacilities');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function fetchAllLocs(){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error fetching all CustomLocations');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function createLoc(loc){
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI, loc).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error creating CustomLocation');
				deferred.reject(errResponse);
			}	
		);
		
		return deferred.promise;
	}
	
	function updateLoc(loc, id){
		var deferred = $q.defer();
		$http.put(REST_SERVICE_URI+id, loc).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error updating CustomLocation');
				deferred.reject(errResponse);
			}
		);
		
		return deferred.promise;
	}
	
	function deleteLoc(id){
		var deferred = $q.defer();
		$http.delete(REST_SERVICE_URI+id).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(errResponse){
				console.error('Error deleting CustomLocation');
				deferred.reject(errResponse);
			}	
		);
		
		return deferred.promise;
	}
	
}]);
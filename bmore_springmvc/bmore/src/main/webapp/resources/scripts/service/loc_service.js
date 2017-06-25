'use strict';

angular.module('locApp').factory('LocService', ['$http', '$q', function($http, $q){
	var REST_SERVICE_URI = 'http://localhost:8080/app/location/';
	
	var factory = {
		fetchAllLocs: fetchAllLocs,
		createLoc: createLoc,
		updateLoc: updateLoc,
		deleteLoc: deleteLoc
	}
	
	return factory;
	
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
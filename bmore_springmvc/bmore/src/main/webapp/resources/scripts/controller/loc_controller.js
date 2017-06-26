'use strict'

var bike_fac;
var groc;
var trails;
var city_line;

angular.module('locApp').controller('LocController', ['$scope', 'LocService', function($scope, LocService){
	var self = this;
	self.loc = {id:null, name:'', address:'', lat:null, lng:null, accessibility:''};
	self.locs = [];
	
	self.submit = submit; 
	self.edit = edit;
	self.remove = remove;
	self.reset = reset;
	
	fetchAllLocs();
	fetchBikeFacs();
	fetchGrocs();
	fetchTrails();
	fetchCityLine();
	
	function fetchGrocs(){
		LocService.fetchGrocs().then(
			function(d) {
				groc = d;
			},
			function(errResponse) {
				console.error('Error fetching GroceryAccessibility');
			}	
		);
		
	}
	
	function fetchBikeFacs(){
		LocService.fetchBikeFacs().then(
			function(d) {
				bike_fac = d;
			},
			function(errResponse) {
				console.error('Error fetching BikeFacilities');
			}	
		);
		
	}
	
	function fetchTrails(){
		LocService.fetchTrails().then(
			function(d) {
				trails = d;
			},
			function(errResponse) {
				console.error('Error fetching Trails');
			}	
		);
		
	}
	
	function fetchCityLine(){
		LocService.fetchCityLine().then(
			function(d) {
				city_line = d;
			},
			function(errResponse) {
				console.error('Error fetching CityLine');
			}	
		);
		
	}
	
	//Following 4 functions interact with the LocService
	function fetchAllLocs(){
		LocService.fetchAllLocs().then(
			function(d) {
				self.locs = d;
			},
			function(errResponse) {
				console.error('Error fetching CustomLocations');
			}
		);
	}
	
	function createLoc(loc){
		LocService.createLoc(loc).then(
			fetchAllLocs,
			function(errResponse){
				console.error('Error creating CustomLocation');
			}
		);
	}
	
	function updateLoc(loc, id){
		LocService.updateLoc(loc, id).then(
			fetchAllLocs,
			function(errResponse){
				console.error('Error updating CustomLocation');
			}
		);
	}
	
	function deleteLoc(id){
		LocService.deleteLoc(id).then(
			fetchAllLocs,
			function(errResponse){
				console.error('Error deleting CustomLocation');
			}
		);
	}
	
	//Following 4 functions respond to user actions from the locForm
	function submit(){
		if(self.loc.id === null){
			console.log('Saving New CustomLocation', self.loc);
			createLoc(self.loc);
		}
		else{
			updateLoc(self.loc, self.loc.id);
			console.log('Updated with id ', self.loc.id);
		}
		reset();
	}
	
	function edit(id){
		console.log('id to be edited', id);
		for(var i = 0; i < self.locs.legth; i++){
			if(self.locs[i].id === id){
				self.loc = angular.copy(self.locs[i]);
				break;
			}
		}
	}
	
	function remove(id){
		console.log('id to be deleted ', id);
		if(self.loc.id === id){
			//reset locForm
			reset();
		}
		deleteLoc(id);
	}
	
	function reset(){
		self.loc = {id:null, name:'', address:'', lat:null, lng:null, accessibility:''};
		$scope.locForm.$setPristine(); //reset locForm
	}
}]);
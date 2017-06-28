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
	fetchGrocs();
	
	function fetchGrocs(){
		LocService.fetchGrocs().then(
			function(d) {
				groc = convertGrocs(d);
				fetchBikeFacs();
			},
			function(errResponse) {
				console.error('Error fetching GroceryAccessibility');
			}	
		);
		
	}
	
	function fetchBikeFacs(){
		LocService.fetchBikeFacs().then(
			function(d) {
				bike_fac = convertMultiLineString(d);
				fetchTrails();
			},
			function(errResponse) {
				console.error('Error fetching BikeFacilities');
			}	
		);
		
	}
	
	function fetchTrails(){
		LocService.fetchTrails().then(
			function(d) {
				trails = convertMultiLineString(d);
				fetchCityLine();
			},
			function(errResponse) {
				console.error('Error fetching Trails');
			}	
		);
		
	}
	
	function fetchCityLine(){
		LocService.fetchCityLine().then(
			function(d) {
				city_line = convertMultiLineString(d);
				populateMap();
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
		currentLoc.name = $('#lname').val();
		if(currentLoc.id !== null){
			updateLoc(currentLoc, currentLoc.id);
			console.log('Updated with id ', currentLoc.id);
		}
	
		else{
			delete currentLoc.id; // null id interferes with db id generation
			console.log('Saving New CustomLocation', currentLoc);
			createLoc(currentLoc);
		}
		
		reset();
	}
	
	function edit(id){
		console.log('id to be edited', id);
		for(var i = 0; i < self.locs.length; i++){
			if(self.locs[i].id === id){
				self.loc = angular.copy(self.locs[i]);
				currentLoc = self.loc;
				break;
			}
		}
	}
	
	function remove(id){
		console.log('id to be deleted ', id);
		if(confirm('Delete?')){
			if(self.loc.id === id){
				//reset locForm
				reset();
			}
			deleteLoc(id);
		}
	}
	
	function reset(){
		self.loc = {id:null, name:'', address:'', lat:null, lng:null, accessibility:''};
		$scope.locForm.$setPristine(); //reset locForm
	}
}]);
var demoAppRest = angular.module('ScrumApp', []);

demoAppRest.controller('companiesController', function($scope) {
	// projects data model
    $scope.companiesList = [
		{
		    "idUser": 1,
		    "companyName": "Company 1"
		},
		{
		    "idUser": 2,
		    "companyName": "Company 2"
		},
		{
		    "idUser": 3,
		    "companyName": "Company 3"
		}	
    ];
});

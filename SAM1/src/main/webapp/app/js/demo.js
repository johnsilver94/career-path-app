var demoAppRest = angular.module('ScrumApp', []);

demoAppRest.controller('projectsController', function($scope) {
	// projects data model
    $scope.projectsList = [
		{
		    "projectNo": 1,
		    "name": "Project 1",
		    "startDate": "2014-04-10"
		},
		{
		    "projectNo": 2,
		    "name": "Project 1",
		    "startDate": "2014-04-10"
		},
		{
		    "projectNo": 3,
		    "name": "Project 1",
		    "startDate": "2014-04-10"
		}		
    ];
});

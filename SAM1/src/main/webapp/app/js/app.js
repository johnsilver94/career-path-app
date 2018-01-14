// WebClient URL is: 			http://localhost:8080/SCRUM/app
// Rest Data Service URL is: 	http://localhost:8080/SCRUM/data/projects

var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var projectsRestURL = 'http://localhost:8080/SAM/data/companies';

//App navigation control
app.config(
		function($routeProvider){
			$routeProvider
				.when('/', 		{controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view1', {controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view2', {controller:'view2Controller', templateUrl: 'partials/view2.html'})
				.when('/view3', {controller:'view3Controller', templateUrl: 'partials/view3.html'})
				.when('/about', {controller:'aboutController', templateUrl: 'partials/about.html'})
				.otherwise({redirectTo: '/'});
		}
);
console.log(">>>> App config done !!!");
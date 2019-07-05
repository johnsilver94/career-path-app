// WebClient URL is: 			http://localhost:8080/SCRUM/app
// Rest Data Service URL is: 	http://localhost:8080/SCRUM/data/projects

var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var companiesRestURL = 'http://localhost:8080/SAM/data/companies';
var jobSeekersRestURL = 'http://localhost:8080/SAM/data/jobSeekers';
var jobOffersRestURL = 'http://localhost:8080/SAM/data/jobOffers';

//App navigation control
app.config(
		function($routeProvider){
			$routeProvider
				.when('/', 		{controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view1', {controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view2', {controller:'view2Controller', templateUrl: 'partials/view2.html'})
				.when('/view3', {controller:'view3Controller', templateUrl: 'partials/view3.html'})
				.when('/jobSeeker', {controller:'jobSeekerController', templateUrl: 'partials/jobSeeker.html'})
				.when('/jobSeekerForm', {controller:'jobSeekerFormController', templateUrl: 'partials/jobSeekerForm.html'})
				.when('/jobOffer', {controller:'jobOfferController', templateUrl: 'partials/jobOffer.html'})
				.when('/about', {controller:'aboutController', templateUrl: 'partials/about.html'})
				.otherwise({redirectTo: '/'});
		}
);
console.log(">>>> App config done !!!");
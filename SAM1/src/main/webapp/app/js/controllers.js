// main controller
app.controller('mainController', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("mainController");
	
	$scope.companiesSelected = [];
	$scope.JobOffersList = [];
	$scope.JobOfferSelected = [];
	
	$http.get(companiesRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + companiesRestURL);
		$scope.companiesList = data;
	});
}]);

// view1 controller
app.controller('view1Controller', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("view1Controller");
	
	$scope.filterOptions = {
		    filterText: ''
	};
	
	$scope.gridView1Definition = { 
		      data: 'companiesList',
		      selectedItems: $scope.companiesSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['idUser'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      width: 650,
		      heigh: 100,
		      columnDefs: [{ field: "idUser", width: 50, displayName: 'ID'},
		    	  		   { field: "userName", width: 100 , displayName: 'User_Name' },
		    	  		   { field: "passWord", width: 100 , displayName: 'Pass_Word' },
		                   { field: "companyName", width: 400 , displayName: 'Company_Name' }]   
		    };		
	
	$http.get(companiesRestURL)
	.success(function(data){
		console.log("view1Controller::GET URL : " + companiesRestURL);
		$scope.companiesList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view1_name = "Companies list";
}]);


//view2 controller
app.controller('view2Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view2Controller");
	
    $scope.gridView2Definition = { 
      data: 'companiesList',
      selectedItems: $scope.companiesSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      sortInfo: { fields: ['idUser'], directions: ['asc'] },
      width: 650,
      heigh: 100,
      columnDefs: [{ field: "idUser", width: 50, displayName: 'ID'},
 		   			{ field: "userName", width: 100 , displayName: 'User_Name' },
 		   			{ field: "passWord", width: 100 , displayName: 'Pass_Word' },
 		   			{ field: "companyName", width: 400 , displayName: 'Company_Name' }]
    };

	restResource.get(companiesRestURL).then(function (data) {
		console.log(data);
		$scope.companiesList = data;
		$timeout(function() {
			idx = 0;
			console.log("company to select [back]: ");
			console.log($scope.companiesSelected[0]);			
			if ($scope.companiesSelected[0] != null){
				for(i in $scope.companiesList){
					if ($scope.companiesList[i].projectNo == $scope.companiesSelected[0].idUser)
						idx = i;
				}
			}
			try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view2Controller: add action");
    	company = $scope.companiesSelected[0];
    	newCompany = JSON.parse(JSON.stringify(company));
    	//
    	newCompany.idUser = 9999;
    	newCompany.companyName = "New company";
    	newCompany.listJobOffer = [];
    	
    	newCompany.link.href = newCompany.link.href.replace(company.idUser, newCompany.idUser);
    	console.log(newCompany.link.href);
    	
    	//
    	$scope.companiesList.push(newCompany);
    	idx = $scope.companiesList.indexOf(newCompany);
    	
    	$timeout(function() {
    		console.log(idx);
    		$timeout(function() { $scope.gridOptions.selectRow(idx, true); });
    	});    	
    	
    };
    
    $scope.save = function(){
    	console.log("view2Controller: save action");
    	if($scope.companiesSelected[0] == null)
    		return;
    	company = $scope.companiesSelected[0];
    	restResource.post(company);	
    };
    
    $scope.cancel = function(){
    	console.log("view2Controller: cancel action");
    };    
    
    $scope.remove = function(){
    	console.log("view2Controller: remove action");
    	company = $scope.companiesSelected[0];
    	link = company.link.href;
    	
    	// remove local model
    	var idx = $scope.companiesList.indexOf(company);      	
    	
    	restResource.remove(company.idUser).then(function(data){
    		$scope.companiesList.splice(idx, 1);
    		$timeout(function() { $scope.gridOptions.selectRow(0, true); });    		
    	});
    	
    };    
    
    $scope.go = function ( path ) {
    	  $location.path( path );
    };
}]);

//view3 controller
app.controller('view3Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view3Controller");
	
    $scope.gridDetailView3Definition = { 
      data: 'JobOffersList',
      selectedItems: $scope.JobOfferSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 300,
      columnDefs: [{ field: "idOffer", width: 120, displayName: 'Id', enableCellEdit: true},
                   { field: "responsabilities", width: 180 , displayName: 'Responsabilities', enableCellEdit: true }
                   ]
    };		
	
	// companies data model
    JobOfferRestURL = $scope.companiesSelected[0].link.href;
	console.log(" JobOfferRestURL:::: " + JobOfferRestURL);
	//
	restResource.get(JobOfferRestURL).then(function (data) {
		console.log(data);
		$scope.company = data;
		$scope.JobOffersList = $scope.company.jobOffer;
		console.log("check resource: ");
		console.log(restResource.entity);
		console.log($scope.company.jobOffer[0].idOffer);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view3Controller: save action");
    	console.log($scope.company);
    	if($scope.company == null)
    		return;  	
    	restResource.put();
    };	
}]);
//about
app.controller('about', 
	  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
	  function($scope, $http, $timeout, $location, restResource) {
		
		  $scope.go = function ( path ) {
		  	  $location.path( path );
			}; 
}]);
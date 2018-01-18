// main controller
app.controller('mainController', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("mainController");
	
	$scope.companiesSelected = [];
	$scope.jobSeekersSelected = [];
	$scope.jobOffersSelected = [];
	
	$http.get(companiesRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + companiesRestURL);
		$scope.companiesList = data;
	});
	$http.get(jobSeekersRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + jobSeekersRestURL);
		$scope.jobSeekerList = data;
	});
	$http.get(jobOffersRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + jobOffersRestURL);
		$scope.jobOfferList = data;
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
			idx = 1;
			console.log("company to select [back]: ");
			console.log($scope.companiesSelected[0]);			
			if ($scope.companiesSelected[0] != null){
				for(i in $scope.companiesList){
					if ($scope.companiesList[i].idUser == $scope.companiesSelected[0].idUser)
						idx = i;
				}
			}
			try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view2Controller: add action");
    	company = $scope.companiesList[0];
    	newCompany = JSON.parse(JSON.stringify(company));

    	newCompany.idUser = null;
    	newCompany.companyName = "New record";
    	
    	idx = $scope.companiesList.length-2;
    	
    	restResource.post(newCompany);
    	$scope.companiesList.push(newCompany);
    	location.reload();
    	
		console.log(idx);
		try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}    	
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
    	$scope.gridView2Definition.selectRow(0, true);
    };    
    
    $scope.remove = function(){
    	console.log("view2Controller: remove action");
    	company = $scope.companiesSelected[0];
    	link = company.link.href;
    	
    	// remove local model
    	var idx = $scope.companiesList.indexOf(company);      	
    	
    	restResource.remove(company).then(function(data){
    		$scope.companiesList.splice(idx, 1);
    		$timeout(function() { $scope.gridView2Definition.selectRow(0, true); });    		
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
		$scope.company = data;
		$scope.JobOffersList = $scope.company.jobOffer;
		console.log("check resource: ");
		console.log(restResource.entity);
		if($scope.company.jobOffer.length != 0)
		{
			console.log($scope.company.jobOffer[0].idOffer);
		}
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view3Controller: save action");
    	if($scope.companiesSelected[0] == null)
    		return;
    	company = $scope.companiesSelected[0];
    	restResource.get(companiesRestURL).then(function (data) {
    		console.log(data);
    		});  
    	restResource.post(company);
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
app.controller('jobSeekerController', 
		  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
		  function($scope, $http, $timeout, restResource) {	// implementation
			console.log("jobSeekerController");
			
			$scope.filterOptions = {
				    filterText: ''
			};
			
			$scope.gridjobSeekerDefinition = { 
				      data: 'jobSeekerList',
				      selectedItems: $scope.jobSeekersSelected,
				      enablePaging: true,
				      multiSelect: false,
				      resizable: true,
				      showFilter: true, showColumnMenu:true,
				      sortInfo: { fields: ['idUser'], directions: ['asc'] },
				      filterOptions: $scope.filterOptions,
				      width: 550,
				      heigh: 100,
				      columnDefs: [{ field: "idUser", width: 50, displayName: 'ID'},
				    	  		   { field: "userName", width: 100 , displayName: 'User_Name' },
				    	  		   { field: "passWord", width: 100 , displayName: 'Pass_Word' },
				    	  		   { field: "age", width: 100 , displayName: 'Age' },
				    	  		   { field: "name", width: 100 , displayName: 'Name' },
				    	  		   { field: "surname", width: 100 , displayName: 'UserName' }]   
				    };		
			
			$http.get(jobSeekersRestURL)
			.success(function(data){
				console.log("jobSeekerController::GET URL : " + jobSeekersRestURL);
				$scope.jobSeekerList = data;
			})
			.error(function(data){
				console.log('ERROR');
				console.log(data);
			});		
			
			$scope.jobSeeker_name = "JobSeekers list";
}]);

app.controller('jobSeekerFormController', 
		  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
		  function($scope, $http, $timeout, $location, restResource) {	// implementation
			console.log("jobSeekerFormController");
			
		    $scope.gridjobSeekerFormDefinition = { 
		      data: 'jobSeekerList',
		      selectedItems: $scope.jobSeekersSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      sortInfo: { fields: ['idUser'], directions: ['asc'] },
		      width: 550,
		      heigh: 100,
		      columnDefs: [{ field: "idUser", width: 50, displayName: 'ID'},
		    	  		   { field: "userName", width: 100 , displayName: 'User_Name' },
		    	  		   { field: "passWord", width: 100 , displayName: 'Pass_Word' },
		    	  		   { field: "age", width: 50 , displayName: 'Age' },
		    	  		   { field: "name", width: 100 , displayName: 'Name' },
		    	  		   { field: "surname", width: 100 , displayName: 'UserName' }]   
		    };

			restResource.get(jobSeekersRestURL).then(function (data) {
				console.log(data);
				$scope.jobSeekerList = data;
				$timeout(function() {
					idx = 0;
					console.log("jobSeekerList to select [back]: ");
					console.log($scope.jobSeekersSelected[0]);			
					if ($scope.jobSeekersSelected[0] != null){
						for(i in $scope.jobSeekerList){
							if ($scope.jobSeekerList[i].idUser == $scope.jobSeekersSelected[0].idUser)
								idx = i;
						}
					}
					try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}
				});
			});	    
		    
		    $scope.add = function(){
		    	console.log("jobSeekerFormController: add action");
		    	jobSeeker = $scope.jobSeekersSelected[0];
		    	newJobSeeker = JSON.parse(JSON.stringify(jobSeeker));
		    	//
		    	newJobSeeker.idUser = null;
		    	newJobSeeker.age = 99;
		    	newJobSeeker.name = "New";
		    	newJobSeeker.surname = "New";
		    	
		    	restResource.post(newJobSeeker);
		    	location.reload();
		    	
		    	$timeout(function() {
		    		console.log(idx);
		    		$timeout(function() { $scope.gridOptions.selectRow(idx, true); });
		    	});    	
		    	
		    };
		    
		    $scope.save = function(){
		    	console.log("jobSeekerFormController: save action");
		    	if($scope.jobSeekersSelected[0] == null)
		    		return;
		    	jobSeeker = $scope.jobSeekersSelected[0];
		    	restResource.post(jobSeeker);
		    };
		    
		    $scope.cancel = function(){
		    	console.log("jobSeekerFormController: cancel action");
		    };    
		    
		    $scope.remove = function(){
		    	console.log("jobSeekerFormController: remove action");
		    	jobSeeker = $scope.jobSeekersSelected[0];
		    	
		    	// remove local model
		    	var idx = $scope.jobSeekerList.indexOf(jobSeeker);      	
		    	
		    	restResource.remove(jobSeeker).then(function(data){
		    		$scope.jobSeekerList.splice(idx, 1);
		    		$timeout(function() { $scope.gridjobSeekerFormDefinition.selectRow(0, true); });    		
		    	});
		    	
		    };    
		    
		    $scope.go = function ( path ) {
		    	  $location.path( path );
		    };
}]);
app.controller('jobOfferController', 
		  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
		  function($scope, $http, $timeout, restResource) {	// implementation
			console.log("jobOfferController");
			
			$scope.filterOptions = {
				    filterText: ''
			};
			
			$scope.gridjobOfferDefinition = { 
				      data: 'jobOfferList',
				      selectedItems: $scope.jobOffersSelected,
				      enablePaging: true,
				      multiSelect: false,
				      resizable: true,
				      showFilter: true, showColumnMenu:true,
				      sortInfo: { fields: ['idOffer'], directions: ['asc'] },
				      filterOptions: $scope.filterOptions,
				      width: 550,
				      heigh: 100,
				      columnDefs: [{ field: "idOffer", width: 50, displayName: 'ID'},
				    	  		   { field: "careerLevel", width: 100 , displayName: 'Career Level' },
				    	  		   { field: "educationRequirements", width: 100 , displayName: 'Education Requirements' },
				    	  		   { field: "responsabilities", width: 100 , displayName: 'Responsabilities' },
				    	  		   { field: "nameworkHours", width: 100 , displayName: 'Work Hours' },
				    	  		   { field: "workEnvironment", width: 100 , displayName: 'Work Environment' }],
				      afterSelectionChange: function(data) {
				    	  if(data.selected)
				    		  {
						    	  $scope.jobOfferSkillList = $scope.jobOffersSelected[0].skill;
				    		  }
				      }
				    };	
			$scope.gridjobOfferSkillDefinition = { 
				      data: 'jobOfferSkillList',
				      selectedItems: $scope.jobOffersSkillSelected,
				      enablePaging: true,
				      multiSelect: false,
				      resizable: true,
				      showFilter: true, showColumnMenu:true,
				      sortInfo: { fields: ['idSkill'], directions: ['asc'] },
				      filterOptions: $scope.filterOptions,
				      width: 250,
				      heigh: 25,
				      columnDefs: [{ field: "idSkill", width: 50, displayName: 'ID'},
				    	  		   { field: "description", width: 200 , displayName: 'Description' }]
				    };	
			
			$http.get(jobOffersRestURL)
			.success(function(data){
				console.log("jobOffersController::GET URL : " + jobOffersRestURL);
				$scope.jobOfferList = data;
			})
			.error(function(data){
				console.log('ERROR');
				console.log(data);
			});	
			
			$scope.go = function ( path ) {
			  	  $location.path( path );
				};
			
			$scope.jobOffer_name = "jobOffers list";
}]);

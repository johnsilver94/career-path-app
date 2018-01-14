// main controller
app.controller('mainController', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("mainController");
	
	$scope.projectsSelected = [];
	$scope.releasesList = [];
	$scope.releaseSelected = [];
	
	$http.get(projectsRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + projectsRestURL);
		$scope.projectsList = data;
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
		      data: 'projectsList',
		      selectedItems: $scope.projectsSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['projectNo'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      width: 420,
		      heigh: 100,
		      columnDefs: [{ field: "projectNo", width: 120, displayName: 'ID'},
		                   { field: "name", width: 300 , displayName: 'Name' }]   
		    };		
	
	$http.get(projectsRestURL)
	.success(function(data){
		console.log("view1Controller::GET URL : " + projectsRestURL);
		$scope.projectsList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view1_name = "Project list";
}]);


//view2 controller
app.controller('view2Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view2Controller");
	
    $scope.gridView2Definition = { 
      data: 'projectsList',
      selectedItems: $scope.projectsSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      sortInfo: { fields: ['projectNo'], directions: ['asc'] },
      width: 420,
      heigh: 100,
      columnDefs: [{ field: "projectNo", width: 120, displayName: 'ID'},
                   { field: "name", width: 300 , displayName: 'Name' }]
    };

	restResource.get(projectsRestURL).then(function (data) {
		console.log(data);
		$scope.projectsList = data;
		$timeout(function() {
			idx = 0;
			console.log("project to select [back]: ");
			console.log($scope.projectsSelected[0]);			
			if ($scope.projectsSelected[0] != null){
				for(i in $scope.projectsList){
					if ($scope.projectsList[i].projectNo == $scope.projectsSelected[0].projectNo)
						idx = i;
				}
			}
			try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view2Controller: add action");
    	project = $scope.projectsSelected[0];
    	newProject = JSON.parse(JSON.stringify(project));
    	//
    	newProject.projectNo = 9999;
    	newProject.name = "New project 9999";
    	newProject.releases = [];
    	
    	today = new Date();
    	dd = today.getDate();
    	mm = today.getMonth()+1; //January is 0!
    	yyyy = today.getFullYear();
    	if (mm < 10)
    		mm = '0' + mm;
    	newProject.startDate = yyyy + "-" + mm + "-" + dd;
    	console.log(newProject.startDate);
    	newProject.link.href = newProject.link.href.replace(project.projectNo, newProject.projectNo);
    	console.log(newProject.link.href);
    	
    	//
    	$scope.projectsList.push(newProject);
    	idx = $scope.projectsList.indexOf(newProject);
    	
    	$timeout(function() {
    		console.log(idx);
    		$timeout(function() { $scope.gridOptions.selectRow(idx, true); });
    	});    	
    	
    };
    
    $scope.save = function(){
    	console.log("view2Controller: save action");
    	if($scope.projectsSelected[0] == null)
    		return;
    	project = $scope.projectsSelected[0];
    	restResource.post(project);	
    };
    
    $scope.cancel = function(){
    	console.log("view2Controller: cancel action");
    };    
    
    $scope.remove = function(){
    	console.log("view2Controller: remove action");
    	project = $scope.projectsSelected[0];
    	link = project.link.href;
    	
    	// remove local model
    	var idx = $scope.projectsList.indexOf(project);      	
    	
    	restResource.remove(project).then(function(data){
    		$scope.projectsList.splice(idx, 1);
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
      data: 'releasesList',
      selectedItems: $scope.releaseSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "releaseId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "indicative", width: 180 , displayName: 'Indicative', enableCellEdit: true },
                   { field: "publishDate", width: 300 , displayName: 'PublishDate', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
    projectRestURL = $scope.projectsSelected[0].link.href;
	console.log("projectsRestURL:::: " + projectRestURL);
	//
	restResource.get(projectRestURL).then(function (data) {
		console.log(data);
		$scope.project = data;
		$scope.releasesList = $scope.project.releases;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view3Controller: save action");
    	console.log($scope.project);
    	if($scope.project == null)
    		return;  	
    	restResource.put();
    };
	
}]);
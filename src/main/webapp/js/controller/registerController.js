app.controller('registerController',function($scope,$controller,registerService){
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.register=function(){
		registerService.register($scope.entity).success(
			function(response){
				if(response.success){
					alert(response.message);
					location.href='index.html';
				}else{
					alert(response.message);
					$scope.entity={};
				}
			}
		)
	}
})
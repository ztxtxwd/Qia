app.controller('indexController',function($scope,$controller,commentService){
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.advise=function(){
		commentService.advise($scope.advice,$scope.user.username).success(
			function(response){
				alert(response.message);
				$scope.advice='';
			}
		)
	}
})
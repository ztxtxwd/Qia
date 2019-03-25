app.controller('commentController',function($scope,commentService){
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.reply=function(){
		commentService.reply().success(
			function(response){
				//alert(response.message);
				$scope.getDetail();
			}
		)
	}
})
app.controller('loginController',function($scope,$controller,loginService){
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.login=function(){
		loginService.login($scope.entity).success(
			function(response){
				if(response.success){
					
					sessionStorage.setItem('username',$scope.entity.username);//设置

					sessionStorage.setItem('userId',response.message);//设置

					location.href='index.html';
				}else{
					alert(response.message);
					$scope.entity={};
				}
			}
		)
	}
})
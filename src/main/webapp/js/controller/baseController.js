//基本控制层
app.controller('baseController' ,function($scope,$http){
	
	$scope.jsonToString=function(jsonString,key){
		var json=JSON.parse(jsonString);
		var value="";
		for(var i=0;i<json.length;i++){
			if(i>0){
				value+=",";
			}
			value+=json[i][key];
		}
		return value;
	}
	$scope.reloadList=function(){
			$scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
		}
	$scope.paginationConf={
				currentPage:1,
				totalItems:10,
				itemsPerPage:10,
				perPageOptions:[10,20,30,40,50],
				onChange:function(){
					$scope.reloadList();
				}
		};
	$scope.user={username:'',id:''};
	$scope.setUsername=function(currentPage){
		if(sessionStorage.getItem('username')==null){
			location.href='login.html'
			return;
		}
		$scope.user.username=sessionStorage.getItem('username');
		$scope.user.id=sessionStorage.getItem('userId');
		$scope.currentPage=currentPage;
	}
	$scope.selectIds=[];
	$scope.updateSelection=function($event,id){
			if($event.target.checked){
				$scope.selectIds.push(id);
			}else{
				var idx=$scope.selectIds.indexOf(id);
				$scope.selectIds.splice(idx,1);
			}
		}
	$scope.signout=function(){
		$scope.user.username='';
		$scope.user.id='';
		sessionStorage.removeItem('username');
		sessionStorage.removeItem('userId');
		location.href='login.html';
	}
});
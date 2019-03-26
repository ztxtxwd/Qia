app.controller('questionController',function($scope,$controller,$location,questionService){
	$controller('baseController',{$scope:$scope});//继承
	$scope.submitQuestion=function(){
		$scope.question.uid=$scope.user.id;
		questionService.submitQuestion($scope.question).success(
			function(response){
				if(response.success){
					alert(response.message);
					location.href='questions.html';
				}else{
					alert(response.message);
				}
			}
		)
	}
	
	$scope.getQuestionList=function(pageNum,pageSize){
		$scope.user.username=sessionStorage.getItem('username');
		$scope.user.id=sessionStorage.getItem('userId');

		questionService.getQuestionList(pageNum,pageSize).success(
			function(response){
				$scope.questionList=response.rows;
			}
		)
	}
	var id=$location.search()['id'];
	$scope.getDetail=function(){
		questionService.getDetail(id,$scope.paginationConf.currentPage,$scope.paginationConf.totalItems).success(
			function(response){
				$scope.fullQuestion=response;
			}
		)
	}
	var keyword=$location.search()['keyword'];
	$scope.search=function(){
		questionService.search(keyword).success(
			function(response){
				$scope.searchResult=response;
			}
		)
	}
	
	$scope.comment={content:'',uid:'',uname:''};
	$scope.reply=function(){
		$scope.comment.uid=sessionStorage.getItem('userId');
		$scope.comment.uname=sessionStorage.getItem('username');
		$scope.comment.qid=id;
		questionService.reply($scope.comment).success(
			function(response){
				if(response.success){
					$scope.comment.content='';
					//alert(response.message);
					$scope.getDetail();
				}else{
					alert(response.message);
				}
				
			}
		)
	}
})
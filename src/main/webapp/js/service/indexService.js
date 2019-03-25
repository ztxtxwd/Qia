app.service('indexService',function($http){
	this.getQuestionList=function(){
		return $http.get("./question/findList?pageNum=1&pageSize=9");
	}
	
})
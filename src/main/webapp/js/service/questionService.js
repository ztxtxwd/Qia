app.service('questionService',function($http){
	this.submitQuestion=function(question){
		return $http.post("./question/add",question);
	}
	this.getQuestionList=function(pageNum,pageSize){
		return $http.get("./question/findList?pageNum="+pageNum+"&pageSize="+pageSize);
	}
	this.getDetail=function(id,pageNum,pageSize){
		return $http.get("./question/findOne?id="+id+"&pageNum="+pageNum+"&pageSize="+pageSize)
	}
	this.reply=function(comment){
		return $http.post('./comment/add',comment);
	}
})
app.service('commentService',function($http){
	this.reply=function(comment){
		return $http.post('./comment/add',comment);
	}
	
	this.advise=function(content,username){
		return $http.get('./comment/advise?content='+content+'&username='+username);
	}
})
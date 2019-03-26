app.service('searchService',function($http){
	this.search=function(keyword){
		return $http.get("./search?keyword="+keyword);
	}
	
})
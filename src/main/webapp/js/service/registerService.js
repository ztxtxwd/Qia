app.service('registerService',function($http){
	this.register=function(entity){
		return $http.post('../user/register.do',entity);
	}
	
})
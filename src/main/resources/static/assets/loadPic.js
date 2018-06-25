/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadPage(pageNum,pageSize){
    var url = 'http://http://118.24.51.89:8080/chosen/picture/' + pageNum + '/' + pageSize;
    var json = getPicture(url)
    var picList = json.elements;
	for (var i = 0; i < pageSize && i < picList.length; i++) {
		var img = picList[i];

		var urlSmall = img.smallUrl;
		var urlBig = img.bigUrl;
		var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '</figcaption>';
        div += '</figure>';

		$('#works').append(div);
	}
	//初始化baguetteBox
	//baguetteBox.run('.tz-gallery');
}

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadPageByType(picType,pageNum,pageSize){
    var url = 'http://http://118.24.51.89:8080/query/picture';
	var result = null;
	$.ajax({
	    type: 'POST',
        url: url,
        dataType: "json",
        data: {picType:picType,pageNum:pageNum,pageSize:pageSize},
        async: false,
        cache:false,
        success: function(json) {
	    	result = json;
	    }
    });
    var picList = result.elements;
	for (var i = 0; i < pageSize && i < picList.length; i++) {
		var img = picList[i];
		var urlSmall = img.smallUrl;
		var urlBig = img.bigUrl;
		var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '</figcaption>';
        div += '</figure>';

		$('#works').append(div);
	}
	//初始化baguetteBox
	//baguetteBox.run('.tz-gallery');
}

function getPicture(url){
	var result = null;
	$.ajax({
	    type: "get",
        url: url,
        dataType: "json",
        async: false,
        cache:false,
        success: function(json) {
	    	result = json;
	    }
    });
    return result;
}

//从url取参数
function getQueryVariable(variable){
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
    }
    return(false);
}
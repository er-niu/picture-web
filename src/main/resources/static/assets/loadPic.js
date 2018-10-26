var local_storage_key = "yywallpaper"
//var url_pre = "http://localhost:8080"
var url_pre = "http://yywallpaper.top"

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadPage(pageNum,pageSize){
    var url = url_pre + '/chosen/picture/' + pageNum + '/' + pageSize;
    var json = getPicture(url)
    var picList = json.elements;
	for (var i = 0; i < pageSize && i < picList.length; i++) {
		var img = picList[i];
		var urlSmall = img.smallUrl;
		var urlBig = img.bigUrl;
		var imgId = img.id;
		var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '<p>'+img.showTime+'</p>';
        div += '</figcaption>';

        // 加小心心
        if(loadStorage().indexOf(imgId.toString()) >= 0){
            div += '<div id="heart'+imgId+'" class="heart heartAnimation" rel="unlike" onclick="beatDemo('+imgId+')" style="background-position: right;" ></div>';
        }else{
            div += '<div id="heart'+imgId+'" class="heart" rel="like" onclick="beatDemo('+imgId+')"></div>';
        }

        div += '</figure>';

		$('#works').append(div);
	}
	//初始化baguetteBox
	//baguetteBox.run('.tz-gallery');
}

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function searchPic(pageNum,pageSize, title){
    var url = url_pre + '/search/picture/' + pageNum + '/' + pageSize + '/' + title;
    var json = getPicture(url)
    var picList = json.elements;
	for (var i = 0; i < pageSize && i < picList.length; i++) {
		var img = picList[i];
		var urlSmall = img.smallUrl;
		var urlBig = img.bigUrl;
		var imgId = img.id;
		var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '<p>'+img.showTime+'</p>';
        div += '</figcaption>';

        // 加小心心
        if(loadStorage().indexOf(imgId.toString()) >= 0){
            div += '<div id="heart'+imgId+'" class="heart heartAnimation" rel="unlike" onclick="beatDemo('+imgId+')" style="background-position: right;" ></div>';
        }else{
            div += '<div id="heart'+imgId+'" class="heart" rel="like" onclick="beatDemo('+imgId+')"></div>';
        }

        div += '</figure>';

		$('#works').append(div);
	}
	//初始化baguetteBox
	//baguetteBox.run('.tz-gallery');
}

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadPageByType(picType,pageNum,pageSize){
    var url = url_pre + '/query/picture';
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
	    var imgId = img.id;
		var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '<p>'+img.showTime+'</p>';
        div += '</figcaption>';

        // 加小心心
        if(loadStorage().indexOf(imgId.toString()) >= 0){
            div += '<div id="heart'+imgId+'" class="heart heartAnimation" rel="unlike" onclick="beatDemo('+imgId+')" style="background-position: right;" ></div>';
        }else{
            div += '<div id="heart'+imgId+'" class="heart" rel="like" onclick="beatDemo('+imgId+')"></div>';
        }

        div += '</figure>';

		$('#works').append(div);
	}
	//初始化baguetteBox
	//baguetteBox.run('.tz-gallery');
}

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadLikePic(json,pageNum,pageSize){
	for (var i = pageNum * pageSize; i < (pageNum + 1) * pageSize && i < json.length; i++) {
        var imgId = json[i];
        var url = url_pre + '/picture/' + parseInt(imgId);
        var img = getPicture(url);
        var urlSmall = img.smallUrl;
        var urlBig = img.bigUrl;
        var div = "";

        div += '<figure class="effect-oscar  wowload fadeInUp">';
        div += '<img src="'+urlSmall+'" alt="'+img.title+'"/>';
        div += '<figcaption>';
        div += '<a href="'+urlBig+'" title="" data-gallery>'+img.title+'</a>';
        div += '<p>'+img.title+'</p>';
        div += '<p>'+img.showTime+'</p>';
        div += '</figcaption>';

        // 加小心心
        if(loadStorage().indexOf(imgId.toString()) >= 0){
            div += '<div id="heart'+imgId+'" class="heart heartAnimation" rel="unlike" onclick="beatDemo('+imgId+')" style="background-position: right;" ></div>';
        }else{
            div += '<div id="heart'+imgId+'" class="heart" rel="like" onclick="beatDemo('+imgId+')"></div>';
        }

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

function beatDemo(imgId){
	var heart = $('#heart'+imgId).attr("rel");
    if(heart == 'like') {
        $('#heart'+imgId).addClass("heartAnimation").attr("rel","unlike");
        $('#heart'+imgId).css("background-position","right");
        addPicToStorage(imgId);
        mAlert('图片已收藏到 \"我的\"','success',1500);
    }else{
        $('#heart'+imgId).removeClass("heartAnimation").attr("rel","like");
        $('#heart'+imgId).css("background-position","left");
        removePicFromStorage(imgId);
    }
}

/** 读取数据，浏览器缓存信息 */
function loadStorage(){
	var storage = localStorage.getItem(local_storage_key);
	if(storage == null || storage == undefined || storage == "")
		storage = new Array();
	else
		storage = storage.split(",")
	return storage;
}

/** 新增一个img到本地存储 */
function addPicToStorage(imgId){
	var storage = loadStorage();
	if(storage.indexOf(imgId.toString()) < 0){
		storage.unshift(imgId);
		saveStorage(storage);
        var url = url_pre + '/like/picture/' + parseInt(imgId);
        getPicture(url);
	}
}

/** 从本地存储移除元素 */
function removePicFromStorage(imgId){
	var storage = loadStorage();
	var img_index = storage.indexOf(imgId.toString());
	if (img_index > -1) {
        storage.splice(img_index, 1);
    }
	saveStorage(storage);
    var url = url_pre + '/like/picture/remove/' + parseInt(imgId);
    getPicture(url);
}

function saveStorage(storage){
	value = JSON.stringify(storage)
	localStorage.setItem(local_storage_key, storage);
}

/**
 * text 消息 // 可以带h4等标签
 * style：状态 info success warning error //默认info
 * position：位置 top-right top-left top-center bottom-left bottom-center bottom-right //默认top-center
 * autoclose：自动关闭时间 //默认3秒
 */
function mAlert(text,style,times){
	if(!style) style = 'info';
	if(!times) times = 3000;
	//详细用法百度 smallPop
	spop({template: text,style: style, position: 'top-center',autoclose: times});
}
var pageNum = 0;//页码
var pageSize = 24;//单页显示图片个数
var imgPadding = 2;
$(function(){
    //初始化加载
    loadPage(pageNum,pageSize);
    //initPicNum();
    //滚动到底部触发加载
    $(window).scroll(function(event){
        var wScrollY = !!window.scrollY ? window.scrollY : window.pageYOffset; // 当前滚动条位置
        var wInnerH = window.innerHeight; // 设备窗口的高度（不会变）
        var bScrollH = document.body.scrollHeight; // 滚动条总高度
        if (wScrollY + wInnerH >= bScrollH * 0.95) { // 修正为滚动到90%即加载，避免个别设备出现奇怪的 wScrollY + wInnerH < bScrollH 的现象
            pageNum ++;
            loadPage(pageNum,pageSize);
        }
    });
    //mAlert('欢迎使用极简壁纸 <br>按 Ctrl+D 可以收藏到浏览器');
});

/** pageNum 页码 pageSize 一页显示几张 json 数据源 type 区分加载方式 */
function loadPage(pageNum,pageSize){
    var url = 'http://localhost:8080/chosen/picture/' + pageNum + '/' + pageSize;
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
        div += '<a href="'+urlBig+'" title="'+img.title+'" data-gallery>'+img.title+'</a>';
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
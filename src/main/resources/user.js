var base = new Base64();

function initLogin() {
    var token = localStorage.getItem("token");
    if (token) {
        var result = post('/service/bz/initLogin', JSON.stringify({'token': token}));
        if (result && result.code == 0) {
            localStorage.setItem("username", result.result.username);
            localStorage.setItem("token", result.result.token);
        } else if (result && result.code == 303) {
            localStorage.removeItem("username");
            localStorage.removeItem("token");
        }
    }
}

/** 同步登陆信息 */
function initLoginInfo() {

    var username = localStorage.getItem("username");
    var token = localStorage.getItem("token");
    if (token && username) {
        initUserInfo();
        $('#loginLi').css("color", "#0088AA");
        $('#loginLi').text(" " + username + " ");
        $('#loginLi').attr('data-target', '#modal6');
        autoAsyncData();//自动同步
    } else {
        $('#loginLi').removeAttr("style")
        $('#loginLi').text(" 注册/登录 ");
        $('#loginLi').attr('data-target', '#modal4');
        // guideLogin();
    }
}

/** 引导登录 */
function guideLogin() {
    try {
        if ($('#bs-example-navbar-collapse-1').width() >= 1024 && !localStorage.getItem("guideLogin")) {
            introJs().setOption("showBullets", false).setOption("doneLabel", "我知道了").start();
            localStorage.setItem("guideLogin", "1");
        }
    } catch (e) {
    }
}


/** 同步用户信息 主要是图片张数 */
function initUserInfo() {
    var username = localStorage.getItem("username");
    $('#nameInfo').text(" " + username + " ");
    initLocalSize();
    initYunSize();
    initProgressBar();
}

function initLocalSize() {
    var loginNum = loadStorage().length;
    $('#localCount').text("本地保存喜欢图片 :  " + loginNum + " 张");
}

function initYunSize() {
    var yunNum = -1;
    try {
        var token = localStorage.getItem("token");
        if (token) {
            var data = JSON.stringify({'token': token});
            var result = post('/service/bz/getSize', data);
            yunNum = result.result.size
        }
    } catch (e) {
    }
    if (yunNum >= 0)
        $('#yunCount').text("云端保存喜欢图片 :  " + yunNum + " 张");
    else
        $('#yunCount').text("云端的服务器可能是打瞌睡了，别着急，晚一点再试一下");
}

function initSizeAll() {
    initLocalSize();
    initYunSize();
    initProgressBar();
}
function initProgressBar(){
    var loginNum = loadStorage().length;
    var progress = Math.round( loginNum  / 10 );
    var div = '';
    div += '<div style="text-align: center">已喜欢 ' + loginNum + ' / 1000 张 </div>'
    div += '<div class="progress progress-striped active">'
    div += '<div class="progress-bar progress-bar-success" role="progressbar"'
    div += 'aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"'
    div += 'style="width: ' + progress + '%;">'
    div += '</div>'
    div += '</div>'
    $('#progressBar').html(div);
}
function register() {
    trackEvent('register', 'start', '0');
    var mobileRegExp = /^[1][3-9][0-9]{9}$/;
    var emailRegExp = /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
    var nameRegExp = /^[a-zA-Z0-9\u4e00-\u9fa5]{1,32}$/;
    var pwdRegExp = /^[a-zA-Z0-9]{6,32}$/;
    var username = $.trim($('#username5').val());
    var password = $.trim($('#password5').val());
    var password2 = $.trim($('#password52').val());
    var email = $.trim($('#email5').val());
    var mobile = $.trim($('#mobile5').val());
    if (!nameRegExp.test(username)) {
        rm("用户名可以是 汉字 字母 数字，长度在1到32个字之间");
        mAlert('用户名可以是 汉字 字母 数字，长度在1到32个字之间', 'error');
        return false;
    }
    if (password != password2) {
        rm("两次密码输入不一致");
        mAlert('两次密码输入不一致', 'error');
        return false;
    }
    if (!pwdRegExp.test(password)) {
        rm("密码可以是 数字 字母，长度至少6位");
        return false;
    }
    if (!emailRegExp.test(email)) {
        rm("邮箱填写有误，请重新填写邮箱");
        mAlert('邮箱填写有误，请重新填写邮箱', 'error');
        return false;
    }
    if (!mobileRegExp.test(mobile)) {
        rm("手机号填写有误，请重新填写手机号");
        mAlert('手机号填写有误，请重新填写手机号', 'error');
        return false;
    }
    rm('注册中。。。请稍等。。。');
    var data = JSON.stringify({'username': username, 'password': password, 'email': email, 'mobile': mobile});
    var result = post('/service/bz/register', data);
    if (!result) {
        rm('服务器暂时打瞌睡了。。。稍后再试试。。。');
        mAlert('服务器暂时打瞌睡了。。。稍后再试试。。。', 'error');
        return false;
    }
    if (result.code == 0) {
        rm('注册成功,2秒后跳转到登录');
        mAlert('注册成功', 'success');
        $('#username4').val(username);
        setTimeout(function () {
            $('#modal5').modal('hide');
            $('#modal4').modal('show');
        }, 1500);
    } else {
        rm('注册失败: ' + result.msg);
    }
}

function login() {
    trackEvent('login', 'start', '0');
    var username = $.trim($('#username4').val());
    var password = $.trim($('#password4').val());
    if (!username || !password) {
        lm('请将用户名和密码填写完整，如果还没有用户名密码，请先点左下角注册');
        mAlert('用户名密码不正确，如果第一次用先点左下角红字注册！', 'error', 12000);
        return false;
    }
    var data = JSON.stringify({'username': base.encode(username), 'password': base.encode(password)});
    var result = post('/service/bz/login', data);
    if (!result) {
        lm('服务器貌似是打瞌睡了。。。稍后再试试。。。');
        return false;
    }
    if (result.code == 0) {
        if (result.result.token)
            localStorage.setItem("token", result.result.token);
        if (base.decode(result.result.username))
            localStorage.setItem("username", base.decode(result.result.username));
        lm('登录成功');
        mAlert('登录成功', 'success');
        $('#password4').val('');
        initLoginInfo();
        setTimeout(function () {
            $('#modal4').modal('hide');
        }, 500);
    } else {
        lm('登录失败: ' + result.msg);
        mAlert('登录失败啦。。。', 'error');
    }
}

function upAll() {
    // 全上
    try {
        var token = localStorage.getItem("token");
        var json = JSON.stringify(loadStorage());
        var data = JSON.stringify({'token': token, 'json': json});
        var result = post('/service/bz/putJson', data);
        if (result && result.code == 0) {
            localStorage.setItem("upAll", "1");
        } else {
            mAlert("同步失败，稍后再试", "error");
            return false;
        }
    } catch (e) {
        um("同步失败。。。云端服务器可能打瞌睡了。。。晚点再试试吧")
    }
}

// function down() {
//     // 全下
//     try {
//         var token = localStorage.getItem("token");
//         var data = JSON.stringify({'token': token});
//         var result = post('/service/bz/getJson', data);
//         var json = JSON.parse(result.result.json);
//         for (var i in json) {
//             var img = '{\"id\":\"' + json[i].id + '\",\"exn\":\"' + json[i].exn + '\",\"w\":\"' + json[i].w + '\",\"h\":\"' + json[i].h + '\"}';
//             addLoadStorage(img,true);
//         }
//     } catch (e) {
//         um("同步失败。。。云端服务器可能打瞌睡了。。。晚点再试试吧")
//
//     }
// }
function downAll() {
    // 全下 重写版本
    try {
        var token = localStorage.getItem("token");
        var data = JSON.stringify({'token': token});
        var result = post('/service/bz/getJson', data);
        if (!result || result.code != 0) {
            mAlert("同步失败，稍后再试", "error");
            return false;

        }
        var json = JSON.parse(result.result.json);
        if (!json) {
            mAlert("同步失败，稍后再试", "error");
            return false;
        }
        saveStorage(json)

    } catch (e) {
        um("同步失败。。。云端服务器可能打瞌睡了。。。晚点再试试吧")

    }
}

function putOne(obj) {
    // 上1
    try {
        var token = localStorage.getItem("token");
        if (token) {
            var data = JSON.stringify({'token': token, 'json': JSON.stringify(obj)});
            var result = post('/service/bz/putOne', data);
        }
    } catch (e) {
    }
}

function delOne(obj) {
    // 删1
    try {
        var token = localStorage.getItem("token");
        var token = localStorage.getItem("token");
        if (token) {
            var data = JSON.stringify({'token': token, 'json': JSON.stringify(obj)});
            var result = post('/service/bz/delOne', data);
        }
    } catch (e) {
    }
}

function clearLogin() {
    // 防止误操作，先确认是否需要
    localStorage.removeItem("username");
    localStorage.removeItem("token");
    localStorage.removeItem("upAll");
    sessionStorage.removeItem("auto");
    initLoginInfo();
    $('#modal6').modal('hide');
    $('#msg4').text("登录后，可以自动同步喜欢的图片到云端，换电脑也不会丢失");
    mAlert('已清除登录信息', 'error');
}

function asyncData() {
    um("同步中。。。");
    upAll();
    downAll();
    initUserInfo();
    um("同步完成   " + new Date().toLocaleString());
    mAlert('同步成功', 'success');
    initLike();
    trackEvent('data', 'async', 'do');
}

function autoAsyncData() {
    // 自动同步只在首次upAll
    if (!localStorage.getItem("upAll")) {
        upAll();
    }
    downAll();
    initUserInfo();
    setTimeout(function () {
        mAlert('喜欢的图片已自动云同步', 'success', 5000)
    }, 1000);
    initLike();
    // 浏览器关闭之前，只自动同步一次
    sessionStorage.setItem("auto", "1");
    trackEvent('data', 'async', 'auto');
}

function initLike() {
    var json = loadStorage();//数据源
    if (window.location.href.indexOf('likes') >= 0 && json.length > 0) {
        $('#main').html('');
        loadPage(0, 24, json);
    }
}

/** 登陆提示 */
function lm(msg) {
    $('#msg4').css('color', 'red');
    $('#msg4').text(msg);
}

/** 注册提示 */
function rm(msg) {
    $('#msg5').css('color', 'red');
    $('#msg5').text(msg);
}

/** 用户提示 */
function um(msg) {
    $('#warnMessage').css('color', 'red');
    $('#warnMessage').text(msg);
}

function post(url, data) {
    var result = null;
    var baseUrl = 'https://zzzmh.cn';
    // var baseUrl = 'http://127.0.0.1';
    $.ajax({
        contentType: "application/json",
        url: baseUrl + url,
        type: 'post',
        cache: false,
        async: false,
        data: data,
        dataType: 'json',
        success: function (json) {
            result = json;
        },
        error: function () {
            // mAlert("非常抱歉，服务器一不小心又开小差了，请稍后再试，如果一直不行发邮件给我反应一下，18121259693@189.cn", "error", 5000);
        }
    });
    return result;
}


$(function () {
    if (!window.localStorage) {
        mAlert("发现您的浏览器不支持缓存数据，对于'喜欢'等功能会有影响，建议检查设置关闭\"隐私模式\"，或者更换浏览器 例如谷歌或者火狐等浏览器", 'error', 24000);
    }
    initLogin();//确认登录状态
    initLoginInfo();//同步登陆信息
    // 绑定回车键事件 区分不同modal
    $(document).keypress(function (e) {
        if (e.which == 13) {
            // display: block 表示打开
            $(".modal").each(function () {
                var x = $(this).css("display");
                if (x == 'block') {
                    if ('modal4' == $(this).attr('id')) {
                        // 登录
                        login();
                    } else if ('modal5' == $(this).attr('id')) {
                        // 注册
                        register();
                    }
                }
            });
        }
    });

});
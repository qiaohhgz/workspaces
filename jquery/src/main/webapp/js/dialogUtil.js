/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/26/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
if (typeof $ != "function") {
    function $(el) {
        return document.getElementById(el);
    }
}

function getTableScrollTop(el) {
    while (el.className != "yui-dt-bd") {
        if (el.className == "yui-dt-data") {
            return el.parentNode.parentNode.scrollTop;
        }
        el = el.parentNode;
    }
    return 0;
}

function showDiv(el, dialogEl) {
    var scrollTop = getTableScrollTop(el);
    dialogEl.style.left = el.offsetLeft + "px";
    dialogEl.style.top = el.offsetTop - scrollTop + "px";
}


var main = window.frames[0];
var qas = main.$(".qa");
var len = qas.length;
console.log(len);

console.log(qas);
var child = main.frames;
console.log(child.length);
for (var i = 0; i < child.length; i++) {
    var txt = qas[i].innerText;
    txt = txt.replace("正确答案：【", "").replace("】", "").replace("|","");
    console.log(txt);
    child[i].setHTML(txt);
}


javascript:void((function (d) {
    if (window.log === undefined) {
        window.log = function log(msg) {
            if (console.log)console.log(msg);
        }
    }
    if (window.loadScript === undefined) {
        window.loadScript = function loadScript(src, callback) {
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = src;
            if (callback) {
                var evl = {};
                evl.handleEvent = function (e) {
                    callback();
                };
                script.addEventListener('load', evl, true);
            }
            document.getElementsByTagName('head')[0].appendChild(script);
            return;
        }
    }
    loadScript("http://t1.dfs.kuaipan.cn/cdlsched/getdl?fid=128424826435019481&amp;acc_params=entryid:128424826435019481,pickupCode:");
})(document));


javascript:void((function (d) {
    if (window.log === undefined) {
        window.log = function log(msg) {
            if (console.log)console.log(msg);
        }
    }
    function getDoc(win){
        return win.contentDocument || win.contentWindow.document;
    }
    var iframe = window.frames["i"];
    log("100");
    var doc = iframe.contentDocument || iframe.contentWindow.document;
    log("300");
    var ins = doc.getElementById("haloword-lookup");
    log("200");
    log(ins.innerHTML);
})(document));
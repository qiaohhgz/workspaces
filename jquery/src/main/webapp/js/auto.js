var fs = window.frames;
log("length = " + fs.length);
var bs = fs[1].document.getElementsByTagName("body");
log("bs.length = " + bs.length);
var bHtml = bs[0];
bs[0].innerHTML = "Hello world";

if (window.log === undefined) {
    window.log = function log(msg) {
        if (console.log)console.log(msg);
    }
}
//test w3school
if (window.submitTryit === undefined) {
    window.submitTryit = function submitTryit() {
        alert("hello submit");
    }
}

//begin
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
    loadScript(window.prompt("Enter a valid url."), function (data) {
        log("log success");
    })
})(document));


//end

loadScript("http://dl.iteye.com/topics/download/67deba66-9778-3a1f-a538-7973511b63cd", function (data) {
    log("log success");
})
loadScript("http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js", function (data) {
    log("log success");
})

console.log(window.frames[0]);
alert(window.update_mouse_pos);
alert(window.frames[0].length)
alert(window.frames[0].document.update_mouse_pos)
with(window.frames[0]){
    document.removeEventListener("mousemove", update_mouse_pos, true);
    document.removeEventListener("mouseup", on_mouse_up, true);
    document.removeEventListener("mousedown", on_mouse_down, true);
    document.removeEventListener("dblclick", on_mouse_dbclick, true);
}

javascript:void((function (d) {
    var c = window.confirm("escape");
    if (c) {
        c = escape(window.prompt("escape"));
        window.prompt(c, c);
    } else {
        c = unescape(window.prompt("unescape"));
        window.prompt(c, c);
    }
})(document));
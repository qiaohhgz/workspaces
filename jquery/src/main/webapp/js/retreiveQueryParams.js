function retreiveQueryParams() {
    var qsParm = new Array();
    var query = parent.document.URL;
    query = query.substring(query.indexOf('?') + 1, query.length);
    var parms = query.split('&');
    for (var i = 0; i < parms.length; i++) {
        var pos = parms[i].indexOf('=');
        if (pos > 0) {
            var key = parms[i].substring(0, pos);
            var val = parms[i].substring(pos + 1);
            val = val.replace("#", "");
            qsParm[key] = val;
        }
    }
    return qsParm;
}
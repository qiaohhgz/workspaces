onerror = handleError
var errorText = "";
var debugDialog;
function handleError(msg, url, l) {
	document.getElementById("error").innerHTML = msg;
	document.getElementById("url").innerHTML = url;
	document.getElementById("line").innerHTML = l;
	debugDialog.show();
	return true;
}
YUI().use("panel", "dd-plugin", function(Y) {
	debugDialog = new Y.Panel({
		srcNode : '#debugDialog',
		headerContent : 'Error',
		width : 250,
		zIndex : 10,
		centered : true,
		modal : true,
		visible : false,
		render : true,
		plugins : [ Y.Plugin.Drag ]
	});
});

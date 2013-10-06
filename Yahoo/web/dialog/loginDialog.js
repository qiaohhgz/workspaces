var loginDialog;
YUI().use("panel", "dd-plugin", function(Y) {
	loginDialog = new Y.Panel({
		srcNode : '#loginDialog',
		headerContent : 'User Login',
		width : 250,
		zIndex : 5,
		centered : true,
		modal : true,
		visible : false,
		render : true,
		plugins : [ Y.Plugin.Drag ]
	});
});
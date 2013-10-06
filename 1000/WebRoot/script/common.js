commonPage = {
	errorMsg : null,//property
};
dwr.engine.setErrorHandler(function(ex) {
	commonPage.errorMsg = ex;
	alert(ex);
});
function get(element) {
	return $(element);
}
function $(element) {
	return document.getElementById(element);
}
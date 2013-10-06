var pageState = {
	Y : null
}
var appointmentTable;
var appointmentUpdateDialog;
var apCols = [
		{
			key : "day",
			label : "日期",
			className : "text-center",
			formatter : formatDateDayCol
		},
		{
			label : "出勤时间",
			children : [ {
				key : "beginWorkTime",
				label : "开始",
				className : "text-center",
				formatter : formatDateTimeCol
			}, {
				key : "endWorkTime",
				label : "结束",
				className : "text-center",
				formatter : formatDateTimeCol
			} ]
		},
		{
			key : "workHours",
			className : "text-center",
			label : "时数",
		},
		{
			label : "加班时间",
			children : [ {
				key : "beginOverTime",
				label : "开始",
				className : "text-center",
				formatter : formatDateTimeCol
			}, {
				key : "endOverTime",
				label : "结束",
				className : "text-center",
				formatter : formatDateTimeCol
			} ]
		},
		{
			key : "overHours",
			className : "text-center",
			label : "时数",
		},
		{
			key : "description",
			className : "text-center",
			label : "备注",
		},
		{
			key : "day",
			label : "删除",
			allowHTML : true,
			formatter : "<input type='button' onclick='deleteByDay(\"{value}\")' value='删除' />"
		} ];
function formatDateCol(o) {
	if (o.value) {
		return pageState.Y.DataType.Date.format(o.value, {
			format : "%Y-%m-%d"
		});
	}
	return "";
}
function formatDateDayCol(o) {
	if (o.value) {
		return pageState.Y.DataType.Date.format(o.value, {
			format : "%d"
		});
	}
	return "";
}
function formatDateTimeCol(o) {
	if (o.value) {
		return pageState.Y.DataType.Date.format(o.value, {
			format : "%H:%M"
		});
	}
	return "";
}

function display() {
	jAppointment.getList(function(data) {
		appointmentTable.setAttrs({
			data : data.aps
		});
		appointmentTable.render("#appointmentTable");
	});
}

function beginWork() {
	jAppointment.beginWork(function(data) {
		display();
	});
}
function endWork() {
	jAppointment.endWork(function(data) {
		display();
	});
}
function beginOverTime() {
	jAppointment.beginOverTime(function(data) {
		display();
	});
}
function endOverTime() {
	jAppointment.endOverTime(function(data) {
		display();
	});
}

function deleteByDay(date) {
	jAppointment.endOverTime(date, function(data) {
		display();
	});
}

YUI().use("datatable", "panel", "dd-plugin", "datatype-date-format",
		function(Y) {
			pageState.Y = Y;
			appointmentUpdateDialog = new Y.Panel({
				srcNode : '#appointmentUpdateDialog',
				headerContent : 'Update Appointment',
				width : 250,
				zIndex : 5,
				centered : true,
				modal : false,
				visible : false,
				render : true,
			});

			appointmentTable = new Y.DataTable({
				columns : apCols,
				sortable : true
			});
			display();
		});
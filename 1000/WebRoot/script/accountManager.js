var accountPage = {
	table : null,
	panel : null,
	nestedPanel : null,
	account : {
		id : null,
		name : null,
		pwd : null,
		createOn : null
	},
	accountsColumns : [ {
		key : "name",
		lable : "Name",
		allowHTML : true,
		formatter : formatNameCol,
		width : 150,
	}, {
		key : "pwd",
		lable : "pwd",
		width : 100,
	}, {
		key : "createOn",
		lable : "createOn",
		allowHTML : true,
		formatter : formatCreateOnCol,
		width : 150,
	}, {
		key : "id",
		label : "action",
		formatter : formatDeleteBtnCol,
		allowHTML : true,
		width : 200,
	} ],
};

function addAccount() {
	var n = dwr.util.getValue("name");
	var p = dwr.util.getValue("pwd");
	if (n == null || n == "" || p == null || p == "") {
		alert("input name and pwd please.");
		return;
	}
	accountPage.account.name = n;
	accountPage.account.pwd = p;
	JAccount.addAccount(accountPage.account);
	dwr.util.setValue("name", "");
	dwr.util.setValue("pwd", "");
	accountPage.panel.hide();
	displayAccounts();
}
function deleteById(id) {
	JAccount.deleteById(id);
	displayAccounts();
}
function displayAccounts() {
	JAccount.getAll(function(data) {
		accountPage.table.setAttrs({
			data : data
		});
	});
}
function showEditDialog(id) {
	JAccount.getAccountById(id, function(ac) {
		dwr.util.setValue("id", ac.id);
		dwr.util.setValue("name", ac.name);
		dwr.util.setValue("pwd", ac.pwd);
	});
	openEditAccountDialog();
}
function updateAccount() {
	account.id = dwr.util.getValue("id");
	account.name = dwr.util.getValue("name");
	account.pwd = dwr.util.getValue("pwd");
	JAccount.updateAccount(accountPage.account);
	accountPage.panel.hide();
	displayAccounts();
}
function selectAll(flag) {
	var ids = document.getElementsByName("ids");
	for ( var i = 0; i < ids.length; i++) {
		ids[i].checked = flag;
	}
}
function removeSelected() {
	var ids = document.getElementsByName("ids");
	var ar = new Array();
	for ( var i = 0; i < ids.length; i++) {
		if (ids[i].checked) {
			var id = ids[i].id.split("_")[1];
			ar[i] = id;
		}
	}
	JAccount.deleteByArray(ar);
	displayAccounts();
}
function openAddAccountDialog() {
	clearAllAccountFields();
	var btnAddAccount = $("btnAddAccount");
	btnAddAccount.style.display = "inline";
	accountPage.panel.show();
}
function openEditAccountDialog() {
	clearAllAccountFields();
	var btnUpdateAccount = $("btnUpdateAccount");
	btnUpdateAccount.style.display = "inline";
	accountPage.panel.show();
}
function clearAllAccountFields() {
	var btnAddAccount = $("btnAddAccount");
	var btnUpdateAccount = $("btnUpdateAccount");
	btnAddAccount.style.display = "none";
	btnUpdateAccount.style.display = "none";
	dwr.util.setValue("id", '');
	dwr.util.setValue("name", '');
	dwr.util.setValue("pwd", '');
}
function testThrwosException() {
	JAccount.testThrwosException();
}
YUI().use("datatable-scroll", function(Y) {
	accountPage.table = new Y.DataTable({
		columns : accountPage.accountsColumns,
		data : [],
		scrollable : "y",
		height : "250px",
		sortable : [ 'id', 'name' ],
		sortBy : {
			id : 'asc'
		}
	});
	accountPage.table.render("#tab");
});
YUI().use("panel", "datatable-base", "dd-plugin", function(Y) {
	accountPage.panel = new Y.Panel({
		srcNode : '#addAccountDialog',
		headerContent : 'Add A New Account',
		width : 300,
		zIndex : 5,
		centered : true,
		modal : true,
		visible : false,
		render : true,
		plugins : [ Y.Plugin.Drag ]
	});
	displayAccounts();
});

function formatNameCol(o) {
	return "<input type='checkbox' id='ids_" + o.data.id + "' name='ids' />"
			+ o.data.name;
}
function formatCreateOnCol(o) {
	return o != null ? formatDate("YYYY-MM-DD", new Date(o.data.createOn.time))
			: "null";
}
function formatDeleteBtnCol(o) {
	return "<a class='yui3-button' href='javascript:deleteById(" + o.data.id
			+ ")'>delete</a>&nbsp;&nbsp;"
			+ "<a class='yui3-button' href='javascript:showEditDialog("
			+ o.data.id + ")'>edit</a>";
}
function testThrwosException() {
	JAccount.testThrwosException();
}
function addTestData() {
	JAccount.addTestData();
}
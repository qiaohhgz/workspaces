function $(elementId) {
    return document.getElementById(elementId);
}
function $name(elementName) {
    return document.getElementsByName(elementName);
}
function init() {
    dwr.util.useLoadingMessage();
}
function list(currentBook) {
    jBook.getBooks(function (books) {
        buildTable(books, currentBook);
    });
}

function buildTable(books, currentBook) {
    $("books").innerHTML = "";
    addHead($("books"));
    for (var i in books) {
        addRow(books[i].id, books[i].name, (currentBook && books[i].id == currentBook.id));
    }
}

function addHead(table) {
    var row = table.insertRow(-1);
    var cell1 = row.insertCell();
    var cell2 = row.insertCell();
    var cell3 = row.insertCell();
    cell3.innerHTML = "Id";
    cell2.innerHTML = "Name";
    cell1.innerHTML = "Edit";
}

function addRow(id, name, selected) {
    var books = $("books");
    var row = books.insertRow(-1);
    row.id = "book" + id;
    if (selected) {
        row.className = "selected";
    }
    var cell1 = row.insertCell();
    var cell2 = row.insertCell();
    var cell3 = row.insertCell();
    cell3.innerHTML = "<a href=\"detail?bookId=" + id + "\" title=\"\">" + id + "</a>";
    cell2.innerHTML = "<a href=\"book/detail?bookId=" + id + "\" title=\"\">" + name + "</a>";
    cell1.innerHTML = "<button onclick=\"deleteBook(" + id + ")\">删除</button>";
}
function deleteBook(id) {
    jBook.deleteBook(id, {
        callback:function () {
            alert("Delete Success");
            list();
        },
        errorHandler:function () {
            alert("Delete Error");
        }
    });
}

function addOneBook() {
    var book = {};
    var nameInput = $("name");
    book.name = nameInput.value;
    if (book.name) {
        jBook.add(book, list);
    }
    nameInput.value = "";
}

function searchBook() {
    var sn = $("searchName").value;
    if (sn) {
        jBook.searchBook(sn, buildTable);
    } else {
        list();
    }
}
if (typeof Student != 'function') {
	function Student(id, name) {
		this.id = id;
		this.name = name;
		this.books = [];
		this.teachers = [];
	}
}
if (typeof Book != 'function') {
	function Book(name) {
		this.name = name;
	}
}
Book.prototype.toString = function() {
	return "[name:" + this.name + "]";
};

Student.prototype.sayHello = function() {
	alert("id = " + this.id + " name = " + this.name + " books = " + this.books);
};

Array.prototype.add = function(args) {
	this.push(args);
};

var s1 = new Student('0001', 'admin');
var s2 = new Student('0002', 'jim');
var b1 = new Book('Fan xing');
s1.books.add(b1);

s1.sayHello();
s2.sayHello();